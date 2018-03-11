package com.samadhaan4u.config;

/**
 * Created by raghvendra.mishra on 11/03/18.
 */
public abstract class ConfigResources {
    private static final String BASIC_PROPERTIES_FILE_NAME = "basic.properties";
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";

    public static String getBasicPropertiesFileName() {
        return BASIC_PROPERTIES_FILE_NAME;
    }

    public static String getDbPropertiesFileName() {
        return DB_PROPERTIES_FILE_NAME;
    }
}
