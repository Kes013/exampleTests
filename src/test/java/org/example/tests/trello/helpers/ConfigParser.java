package org.example.tests.trello.helpers;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigParser {

    private static final String user = "username";
    private static final String pass = "password";
    private static final String url = "url";
    private static final String path = "src/test/resources/auth.properties";
    private static final Logger LOG = Logger.getLogger(String.valueOf(ConfigParser.class));

    private static String getProperties(String prop) {
        Properties properties = new Properties();
        try {
            File file = new File(path);
            properties.load(new FileReader(file));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return properties.getProperty(prop);
    }

    public static String getUser() {
        return getProperties(user);
    }

    public static String getPass() {
        return getProperties(pass);
    }

    public static String getUrl() {
        return getProperties(url);
    }
}
