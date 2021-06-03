package com.alpha.controller.validation;

import com.alpha.controller.exception.BookDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class CheckBookData {
    private static final Logger logger = LogManager.getLogger(CheckBookData.class);

    public static boolean isYear(String year) {
        int i;
        try {
            i = Integer.parseInt(year);
            if (i < 999 || i > Calendar.getInstance().get(Calendar.YEAR))
                throw new BookDataException("isYear >> Incorrect year",
                year, BookDataException.ARGUMENT.YEAR);
            return true;
        }
        catch (BookDataException | NumberFormatException e) {
            logger.warn(e.toString());
            return false;
        }
    }

    public static boolean isPages(String pages) {
        try {
            if (Integer.parseInt(pages) < 1)
                throw new BookDataException("isPages >> Incorrect pages",
                        pages, BookDataException.ARGUMENT.PAGES);
            return true;
        }
        catch (BookDataException | NumberFormatException e) {
            logger.warn(e.toString());
            return false;
        }
    }

    public static boolean isCost(String cost) {
        try {
            if (Double.parseDouble(cost) < 0.1)
                throw new BookDataException("isPages >> Incorrect cost",
                        cost, BookDataException.ARGUMENT.COST);
            return true;
        }
        catch (BookDataException | NumberFormatException e) {
            logger.warn(e.toString());
            return false;
        }
    }

    public static boolean isInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException e) {
            logger.warn(e.getMessage());
            return false;
        }
    }

    public static boolean isChars(String text) {
        return text.matches("[a-zA-Z]*");
    }
}
