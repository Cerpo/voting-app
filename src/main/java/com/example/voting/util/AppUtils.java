package com.example.voting.util;

public final class AppUtils extends AppUtilsBase {
    public static Integer getNumberOfMPs() {
        return getIntegerProperty("app.mp", "200");
    }
}
