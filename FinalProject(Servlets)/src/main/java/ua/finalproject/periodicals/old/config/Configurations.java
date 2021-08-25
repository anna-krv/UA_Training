package ua.finalproject.periodicals.old.config;

import java.util.Properties;

public class Configurations {
    private static final String PROPERTIES_FILE_PATH = "application.properties";
    private static Configurations instance;
    private Properties properties;

    private Configurations() {
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(PROPERTIES_FILE_PATH));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
    }

    public static String getProperty(String key) {
        if (instance == null) {
            createInstance();
        }
        return instance.properties.getProperty(key);
    }

}
