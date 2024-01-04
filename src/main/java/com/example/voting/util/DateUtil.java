package com.example.voting.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static boolean isValidDateFormat(String dateStr) {

        try {
            LocalDateTime.parse(dateStr + "T00:00:00", df);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDateTime getStartOfDay(String dateStr) {
        return LocalDateTime.parse(dateStr + "T00:00:00", df).with(LocalTime.MIN);
    }

    public static LocalDateTime getEndOfDay(String dateStr) {
        return LocalDateTime.parse(dateStr + "T00:00:00", df).with(LocalTime.MAX);
    }
}
