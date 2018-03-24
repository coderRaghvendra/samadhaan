package com.samadhaan4u.config.system;

import com.samadhaan4u.config.ConfigResources;

import java.io.FileReader;
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
        try{
            FileReader fileReader = new FileReader(ConfigResources.getBasicPropertiesFileName());
            Properties properties = new Properties();
            properties.load(fileReader);
            serverName = properties.getProperty(SERVER_NAME).trim();
            adminMail = properties.getProperty(ADMIN_MAIL).trim();
            mailServerPassword = properties.getProperty(MAIL_SERVER_PASSWORD).trim();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Base Config", e);
        }
    }
}
