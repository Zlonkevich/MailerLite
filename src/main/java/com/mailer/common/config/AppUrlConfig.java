package com.mailer.common.config;

import com.mailer.common.utils.ApplicationPropertiesReader;

public class AppUrlConfig {
    public static final String BASE_URL = ApplicationPropertiesReader.getProperty("frontendBaseUrl", "http://localhost:5173");

    public static class Subscribers {
        public static final String SUBSCRIBERS = BASE_URL + "/subscribers";
        public static final String NEW = BASE_URL + "/subscribers/new";
    }

    public static class Fields {
        public static final String FIELDS = BASE_URL + "/fields";
        public static final String NEW = BASE_URL + "/fields/new";
    }
}
