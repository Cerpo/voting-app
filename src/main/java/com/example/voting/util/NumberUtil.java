package com.example.voting.util;

import java.text.DecimalFormat;

public class NumberUtil {
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public static double trimDouble (double value) {
        return Double.parseDouble(df.format(value).replace(",", "."));
    }
}
