package com.core.webapp.util;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String format(LocalDate date){
        if(date == null) return "";
        return date.equals(NOW) ? "По настоящее время" : date.format(DATE_FORMATTER);
    }
}
