package com.liriansu.atm.util;

public class StringUtils {
    public static String getACid(String string) {
        if (string.startsWith("ac")) {
            string = string.substring(2);
        }
        return string;
    }

    public static String repeat(String string, int times) {
        if (times <= 0) {
            return "";
        } else {
            return new String(new char[times]).replace("\0", string);
        }
    }

    public static String str(Object object) {
        return object.toString();
    }
}
