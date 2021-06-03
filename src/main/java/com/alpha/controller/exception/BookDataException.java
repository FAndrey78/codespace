package com.alpha.controller.exception;

public class BookDataException extends Exception{
    public enum ARGUMENT {NONE, NAME, AUTHOR, PUBLISH, YEAR, PAGES, COST}

    private String value = "";
    private ARGUMENT field = ARGUMENT.NONE;

    public BookDataException(String message) {
        super(message);
    }

    public BookDataException(String message, String value, ARGUMENT field) {
        super(message);
        this.value = value;
        this.field = field;
    }

    public String getValue() {
        return value;
    }
    public ARGUMENT getField() {
        return field;
    }

    @Override
    public String toString() {
        return "BookException{ " +
                "field=" + field + " | " +
                "value=" + value + " | " +
                getMessage() + " }";
    }
}
