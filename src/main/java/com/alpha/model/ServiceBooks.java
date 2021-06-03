package com.alpha.model;

import com.alpha.model.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServiceBooks {
    private static final Logger logger = LogManager.getLogger(ServiceBooks.class);
    private List<Book> bookList;

    public ServiceBooks() {
        bookList = SourceBooks.getFromFile();
    }

    public List<Book> getBookList() {
        return (List<Book>) (new ArrayList<Book>(bookList)).clone();
    }

    public void saveBooks() {
        SourceBooks.setToFile(bookList);
    }

    public void addBook(String name, String author, String publish, int year, int countPages, double cost) {
        bookList.add(new Book(name, author, publish, year, countPages, cost));
    }

    public List<Book> searchBook(String search) {
        if (new Scanner(search).hasNextInt()) {
            return bookList.stream()
                    .filter(Book -> Book.getYear() > Integer.parseInt(search))
                    .collect(Collectors.toList());
        }
        return bookList.stream()
                .filter(Book -> Book.getAuthor().equalsIgnoreCase(search)
                        || Book.getPublish().equalsIgnoreCase(search))
                .collect(Collectors.toList());
    }

    public void changePriceBook (int percent) {
        bookList = bookList.stream()
                .peek(Book -> Book.setCost(Book.getCost() + (Book.getCost() * percent / 100)))
                .collect(Collectors.toList());
        logger.info("The cost of books changed by {} percent.", percent);
    }
}
