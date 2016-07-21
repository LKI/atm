package com.liriansu.atm;

import com.liriansu.atm.entity.AC;
import com.liriansu.atm.util.HTML;
import com.liriansu.atm.util.StringUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.exit(parse(args));
    }

    private static int parse(String[] args) {
        if (1 != args.length) {
            System.err.println("Error: you should specify an ac article ID");
            return 1;
        }
        AC ac = new AC(StringUtils.getACid(args[0]));
        try {
            System.out.println(ac.getUrl());
            String html = HTML.fetch(ac.getUrl());
            System.out.println(html.contains("300"));
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
