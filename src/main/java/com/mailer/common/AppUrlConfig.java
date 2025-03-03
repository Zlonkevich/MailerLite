package com.mailer.common;

import com.mailer.common.utils.ConfigReader;

public class AppUrlConfig {
    public static final String BASE_URL = ConfigReader.getProperty("frontendBaseUrl");

    public static class Subscribers {
        public static final String SUBSCRIBERS = BASE_URL + "/subscribers";
        public static final String NEW = BASE_URL + "/subscribers/new";
    }

    public static class Fields {
        public static final String FIELDS = BASE_URL + "/fields";
        public static final String NEW = BASE_URL + "/fields/new";
    }

}
