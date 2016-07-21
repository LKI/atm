package com.liriansu.atm.util;

public class StringUtils {
    public static String getACid(String string) {
        if (string.startsWith("ac")) {
            string = string.substring(2);
        }
        return string;
    }
}
