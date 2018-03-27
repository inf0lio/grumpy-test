package com.test.core;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Helpers {

    public static Properties getProperties() {
        Properties profileProperties = new Properties();

        try {
            profileProperties.load(Helpers.class.getClassLoader().getResourceAsStream("config.properties"));

            Properties systemProperties = System.getProperties();

            for (Map.Entry entry : profileProperties.entrySet()) {
                String key = String.valueOf(entry.getKey());
                if (systemProperties.containsKey(key)) {
                    String value = systemProperties.getProperty(key);

                    if (!value.isEmpty()) {
                        profileProperties.setProperty(key, value);
                        System.out.println(key + " = " + entry.getValue() + " !!! corrected");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error : config.properties is not exist");
            e.printStackTrace();
        }
        return profileProperties;
    }
}