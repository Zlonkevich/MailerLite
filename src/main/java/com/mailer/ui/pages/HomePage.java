package com.mailer.ui.pages;

import com.mailer.common.config.AppUrlConfig;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends BaseSMNPage {
    private final String URL = AppUrlConfig.BASE_URL;

    public HomePage(Page page) {
        super(page);
    }
}
