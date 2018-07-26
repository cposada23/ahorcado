package com.sofka.taller.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class Props {
    private Properties properties;
    private static Props props;

    private Props() {
        try {
            properties = new Properties();
            properties.load(new FileReader("config.properties"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public  String getProperty(String name) {

        String result = properties.getProperty(name);
        return result;
    }

    public static Props getInstance() {
        if (props == null) {
            props = new Props();
        }
        return props;
    }
}
