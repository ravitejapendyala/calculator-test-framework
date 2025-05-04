package com.google.calculator.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static final Properties properties = new Properties();
    private static ConfigurationManager instance;

    private ConfigurationManager() {
        try (FileInputStream input = new FileInputStream("src/main/resources/configuration.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration properties");
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }

    public String getApplicationUrl() {
        return getProperty("url");
    }
}
