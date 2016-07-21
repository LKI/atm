package com.liriansu.atm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Prop {
    private Properties prop;

    public Prop(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            Properties prop = new Properties();
            prop.load(fis);
        }
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
}
