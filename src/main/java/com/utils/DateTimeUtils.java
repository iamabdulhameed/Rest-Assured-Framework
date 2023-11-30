package com.utils;

import java.time.LocalDateTime;

public class DateTimeUtils {

    public static String getCurrentYear() {
        return String.valueOf(LocalDateTime.now().getYear());
    }
}
