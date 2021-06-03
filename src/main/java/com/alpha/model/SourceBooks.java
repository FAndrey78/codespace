package com.alpha.model;

import com.alpha.model.entity.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SourceBooks {

    private static final Logger logger = LogManager.getLogger(SourceBooks.class);
    private static Path booksFile = Paths.get("books.json").toAbsolutePath();
    private static Random random = new Random();
    private static String[] strAuthor = {"Akunin", "Grimm", "Lermontov", "Prishvin", "Bulgakov", "Orlov", "Shevchenko", "Bunin", "Gorkiy"};
    private static String[] strPublish = {"Lion", "Folio", "Litera", "Fakel", "Utro", "Pravo", "Mahaon"};
    private static String symbols = "abcdefghijklmnopqrstuvwxyz";

    public static List<Book> generateBooks(int countBooks) {
        List<Book> list = new ArrayList<>();
        for (int i=0; i < countBooks; i++){
            list.add( new Book("Book" + (i + 1),
                    strAuthor[random.nextInt(strAuthor.length - 1)],
                    strPublish[random.nextInt(strPublish.length - 1)],
                    random.nextInt(2020 - 1950 + 1) + 1950,
                    random.nextInt(1000 - 100) + 100,
                    (double) Math.round(random.nextDouble() * random.nextInt(10000) * 100) / 100));
        }
        return list;
    }

    public static void setToFile(List<Book> bookList) {
        ObjectMapper mapper = new ObjectMapper();
        try (ObjectOutputStream ostream = new ObjectOutputStream(
                new FileOutputStream(booksFile.toString()))) {
            ostream.writeObject(mapper.writeValueAsString(bookList));
            logger.info("{} books saved to: {}", bookList.size(), booksFile);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static List<Book> getFromFile() {
        List<Book> bookList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try (ObjectInputStream istream = new ObjectInputStream(
                new FileInputStream(booksFile.toString()))) {
            bookList = mapper.readValue((String) istream.readObject(), new TypeReference<List<Book>>() {});
            logger.info("{} books loaded from: {}", bookList.size(), booksFile);
        } catch (IOException | ClassNotFoundException fe) {
            logger.error(fe.getMessage());
//            bookList = generateBooks(5);
//            logger.info("{} temporary books generated.", bookList.size());
        }
        return bookList;
    }
}
