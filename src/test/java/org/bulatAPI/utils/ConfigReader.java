package org.bulatAPI.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;
    static String file_path = "/Users/bukeshi/Desktop/API with Baiastan/restApiB/src/test/resources/properties/config.properties";

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream(file_path);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

    }

    public static String getProperty(String key) {
        return properties.getProperty(key).trim();
    }

}
