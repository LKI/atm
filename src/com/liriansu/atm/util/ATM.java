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

    /**
     * Get ATM version by reading /VERSION resource
     *
     * @return version number
     */
    public static String getVersion() {
        String version = MSG.UNKNOWN;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ATM.class.getClassLoader().getResourceAsStream("/VERSION")))) {
            version = br.readLine().trim();
        } catch (IOException e) {
        }
        return version;
    }
}
