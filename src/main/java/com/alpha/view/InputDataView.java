package com.alpha.view;

import java.util.Scanner;

public class InputDataView {
    private Scanner scanner = new Scanner(System.in);
    private BooksView booksView;

    public InputDataView(BooksView booksView) {
        this.booksView = booksView;
    }

    public int inputMenuItem() {
        booksView.printMenu();
        booksView.printMessage(BooksView.ENTER_COMMAND);
        while ( !scanner.hasNextInt()) {
            scanner.next();
            booksView.printMessage(BooksView.ERROR_ENTER);
        }
        return scanner.nextInt();
    }

    public String inputString(String message) {
        booksView.printMessage(message);
        return scanner.next().trim();
    }
}
