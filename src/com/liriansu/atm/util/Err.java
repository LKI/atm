package com.liriansu.atm.util;

public class Err {
    public static boolean error(String... msgs) {
        for (int i = 0; i < msgs.length; i++) {
            // TODO i18n here
            String indent = i == 0 ? "ERROR: " : "       ";
            System.err.println(indent + msgs[i]);
        }
        return false;
    }

    public static boolean error(Exception e, String... msgs) {
        error(msgs);
        e.printStackTrace(System.err);
        return false;
    }
}
