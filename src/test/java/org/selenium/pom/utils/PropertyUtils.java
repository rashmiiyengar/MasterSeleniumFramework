package org.selenium.pom.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;

public class PropertyUtils {
    private static Properties properties = new Properties();

        public static Properties propertyLoader(String filePath) {


            BufferedReader reader;

            try {
                reader = new BufferedReader(new FileReader(filePath));
            try {
                properties.load(reader);

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file " +filePath);
            }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("properties file not loaded at " +filePath);
            }


        return  properties;
        }



}
