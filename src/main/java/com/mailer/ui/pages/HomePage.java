package com.mailer.ui.pages;

import com.mailer.common.utils.ConfigReader;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends BaseSMNPage {
    private final String URL = ConfigReader.getProperty("frontendBaseUrl");

    public HomePage(Page page) {
        super(page);
    }
}
