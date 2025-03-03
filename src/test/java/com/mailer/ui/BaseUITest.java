package com.mailer.ui;

import com.mailer.ui.config.PlaywrightConfig;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.stereotype.Component;

@Component
public class BaseUITest {
    private static Playwright playwright;
    private static Browser browser;

    private BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = PlaywrightConfig.getBrowser();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }


    @BeforeEach
    public void setup() {
        browserContext = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
        page = browserContext.newPage();
    }

    @AfterEach
    public void teardown() {
        if (browserContext != null) {
            browserContext.close();
        }
    }
}
