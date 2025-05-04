package com.google.calculator.utils;

public class TestUtils {

    public static double parseDisplayValue(String displayValue) {
        // Remove any commas and convert to double
        return Double.parseDouble(displayValue.replace(",", ""));
    }

    public static boolean isApproximatelyEqual(double actual, double expected, double tolerance) {
        return Math.abs(actual - expected) <= tolerance;
    }
}