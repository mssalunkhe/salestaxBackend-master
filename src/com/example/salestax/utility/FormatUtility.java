package com.example.salestax.utility;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FormatUtility {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static String format(double input) {
        input = Math.ceil(input * 20.0) / 20.0;
        return DECIMAL_FORMAT.format(input);
    }
}
