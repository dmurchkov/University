package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private static final String PROPERTY_FILE = "/application.properties";
    private static Properties properties;

    public static String loadProperty(String name) {

        if (name == null || name.isEmpty()) {
            return null;
        }
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(PropertyLoader.class.getResourceAsStream(PROPERTY_FILE));
            } catch (IOException e) {
                e.printStackTrace();
                throw new Error(e);
            }
        }
        return properties.getProperty(name);
    }
}
