package com.wego.util;

import com.wego.main.ExternalUtility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public static Properties loadProperties() {

        try (
                InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            return prop;
            // get the property value and print it out
        } catch (
                IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
