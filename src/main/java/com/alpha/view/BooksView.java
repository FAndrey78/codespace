package com.alpha.view;

import com.alpha.model.entity.Book;

import java.util.List;

public class BooksView {
    public static final String ENTER_COMMAND = "\nMake you choice: ";
    public static final String ERROR_ENTER = "Enter error. Repeat!\n";
    public static final String ENTER_TITLE = "Enter title: ";
    public static final String ENTER_AUTHOR = "Enter author: ";
    public static final String ENTER_PUBLISHER = "Enter publisher: ";
    public static final String ENTER_YEAR = "Enter year: ";
    public static final String ENTER_PAGES = "Enter number pages: ";
    public static final String ENTER_COST = "Enter cost: ";
    public static final String ENTER_SEARCH = "What to find?: ";
    public static final String ENTER_PERCENT = "Change price to (%): ";
    public static final String INFO_NO_BOOKS = "No books!\n";

    public void printMessage(String message) {
        if (message.equalsIgnoreCase(ERROR_ENTER))
            System.err.println(message);
        else System.out.print(message);
    }

    public void printListBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println(BooksView.INFO_NO_BOOKS); return;
        }
        for (Book elem : books) {
            System.out.println(convertBookToString(elem));
        }
    }
    private String convertBookToString(Book book) {
        return String.format("%-15s| %-10s| %-10s| %-5d| %-5d| %8.2f", book.getAuthor(), book.getName(),
                book.getPublish(), book.getYear(), book.getCountPages(), book.getCost());
    }

    public void printMenu() {
        System.out.println("""
                1.View all books
                2.Add a new book
                3.Change price of the book
                4.Search the book (by author, publisher or year)
                0.Close the app\s"""
        );
    }
}
