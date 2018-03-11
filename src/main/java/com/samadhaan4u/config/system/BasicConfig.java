package com.samadhaan4u.config.system;

import com.samadhaan4u.config.ConfigResources;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by raghvendra.mishra on 11/03/18.
 */
public abstract class BasicConfig {
    private static final String SERVER_NAME = "SERVER_NAME";
    private static final String ADMIN_MAIL = "ADMIN_MAIL";
    private static final String MAIL_SERVER_PASSWORD = "MAIL_SERVER_PASSWORD";
    private static String serverName;
    private static String adminMail;
    private static String mailServerPassword;

    public static synchronized boolean initialize() {
        try (InputStream is = ConfigResources.getBasicPropertiesFileName()) {
//            try (InputStream is = SystemConfig.class.getClassLoader().getResourceAsStream(
//                    ConfigResources.getBasicPropertiesFileName())) {
            Properties prop = new Properties();
            prop.load(is);
            resourcesBasePath = prop.getProperty(PROP_RESOURCES_BASE_PATH).trim();
            templateBasePath = prop.getProperty(PROP_TEMPLATE_BASE_PATH).trim();
            seeddataBasePath = prop.getProperty(PROP_SEEDDATA_BASE_PATH).trim();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Base Config", e);
        }
    }
}
