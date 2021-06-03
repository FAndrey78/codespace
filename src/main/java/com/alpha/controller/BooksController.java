package com.alpha.controller;

import com.alpha.controller.validation.CheckBookData;
import com.alpha.model.ServiceBooks;
import com.alpha.view.BooksView;
import com.alpha.view.InputDataView;

public class BooksController {
    private BooksView printBooks;
    private ServiceBooks serviceBooks;
    private InputDataView dataView;

    public BooksController() {
        printBooks = new BooksView();
        serviceBooks = new ServiceBooks();
        dataView = new InputDataView(printBooks);
    }

    public void execute() {
        while (true) {
            System.out.println();
            int menuItem = dataView.inputMenuItem();
            switch (menuItem) {
                case 0: serviceBooks.saveBooks(); System.exit(0);
                case 1: printBooks.printListBooks(serviceBooks.getBookList()); break;
                case 2: inputAddBook(); break;
                case 3: changePriceBook(); break;
                case 4: searchBook(); break;
                default: printBooks.printMessage(BooksView.ERROR_ENTER);
            }
        }
    }

    private void inputAddBook() {
        String name = dataView.inputString(BooksView.ENTER_TITLE);
        String author;
        while (true) {
            author = dataView.inputString(BooksView.ENTER_AUTHOR);
            if (CheckBookData.isChars(author)) break;
            printBooks.printMessage(BooksView.ERROR_ENTER);
        }
        String publisher = dataView.inputString(BooksView.ENTER_PUBLISHER);
        String year;
        while (true) {
            year = dataView.inputString(BooksView.ENTER_YEAR);
            if (CheckBookData.isYear(year)) break;
            printBooks.printMessage(BooksView.ERROR_ENTER);
        }
        String pages;
        while (true) {
            pages = dataView.inputString(BooksView.ENTER_PAGES);
            if (CheckBookData.isPages(pages)) break;
            printBooks.printMessage(BooksView.ERROR_ENTER);
        }
        String cost;
        while (true) {
            cost = dataView.inputString(BooksView.ENTER_COST);
            if (CheckBookData.isCost(cost)) break;
            printBooks.printMessage(BooksView.ERROR_ENTER);
        }
        serviceBooks.addBook(name, author, publisher, Integer.parseInt(year),
                Integer.parseInt(pages), Double.parseDouble(cost));
    }

    private void searchBook() {
        String search = dataView.inputString(BooksView.ENTER_SEARCH);
        printBooks.printListBooks(serviceBooks.searchBook(search));
    }

    private void changePriceBook() {
        String percent;
        while (true) {
            percent = dataView.inputString(BooksView.ENTER_PERCENT);
            if (CheckBookData.isInt(percent)) break;
            printBooks.printMessage(BooksView.ERROR_ENTER);
        }
        serviceBooks.changePriceBook(Integer.parseInt(percent));
    }
}
