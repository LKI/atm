package com.liriansu.atm.util;

import java.io.*;

/**
 * {@link ATM} holds some basic project wide constant and method
 */
public class ATM {
    public static final String DOMAIN     = "acfun.domain";
    public static final String ENCODING   = "html.encoding";
    public static final String FIRST_TIME = "firstTime";
    public static final String PROTOCOL   = "protocol";
    public static final String SH_START   = "shell.start";
    public static final String URL        = "http://www.github.com/LKI/atm";

    /**
     * Get ATM version by reading /VERSION resource
     *
     * @return version number
     */
    public static String getVersion() {
        String version;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ATM.class.getClassLoader().getResourceAsStream("/VERSION")))) {
            version = br.readLine().trim();
        } catch (IOException e) {
            version = MSG.UNKNOWN;
        }
        return version;
    }
}
