package com.example.voting.util;

import java.io.IOException;
import java.util.Properties;

public sealed class AppUtilsBase permits AppUtils {
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(AppUtilsBase.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Integer getIntegerProperty(String propertyName, String defaultValue) {
        return Integer.valueOf(properties.getProperty(propertyName, defaultValue));
    }
}
