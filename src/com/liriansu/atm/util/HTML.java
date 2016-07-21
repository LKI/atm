package com.liriansu.atm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTML {
    public static String fetch(String url) throws IOException {
        String        html = "";
        URL           u    = new URL(url);
        URLConnection conn = u.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), ATM.ENCODING))) {
            String line;
            while ((line = br.readLine()) != null) {
                html += line;
            }
        }
        return html;
    }
}
