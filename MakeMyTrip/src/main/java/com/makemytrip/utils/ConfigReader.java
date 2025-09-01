package com.makemytrip.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    // Properties object to hold configuration key-value pairs
    private static Properties properties = new Properties();
    // Static block to load properties file during class loading
    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis); // Load properties from file
            fis.close(); // Close file input stream
        } catch (IOException e) {
            e.printStackTrace(); // Print detailed error message for debugging
        }
    }
    // Fetch value by key from loaded properties
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
