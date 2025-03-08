package com.example.medicine.util;

public class Validator {
    public static final String INTEGER = "^[0-9]+$";
    public static final String PHONE = "^[0-9]{10,11}$";

    public static boolean isValidate(String regex, String input) {
        return input.matches(regex);
    }

    public static boolean isNullOrEmpty(Object... o) {
        for (Object obj : o) {
            if (obj == null || obj.toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
