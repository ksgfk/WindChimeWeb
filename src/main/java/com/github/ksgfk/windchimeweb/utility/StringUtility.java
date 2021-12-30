package com.github.ksgfk.windchimeweb.utility;

public class StringUtility {
    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.isEmpty();
    }

    public static boolean isNullOrWhiteSpace(String str) {
        if (str == null) {
            return true;
        }
        if (str.isEmpty()) {
            return true;
        }
        return str.isBlank();
    }
}
