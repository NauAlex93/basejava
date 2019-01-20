package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate of(int year, Month month){
        return LocalDate.of(year, month, 1);
    }

    public static String dateToString(LocalDate startDate, LocalDate endDate) {
        return convertDateToString(startDate) + " - " + convertDateToString(endDate);
    }

    private static String convertDateToString(LocalDate date)
    {
        if (date == null)
        {
            return "";
        }
        return date.equals(LocalDate.now()) ? "Сейчас" : date.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }
}
