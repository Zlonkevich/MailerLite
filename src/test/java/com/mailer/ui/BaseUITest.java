package com.mailer.ui;

import com.mailer.ui.config.PlaywrightConfig;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class BaseUITest {
    private BrowserContext browserContext;
    private Browser browser;
    protected Page page;

    @BeforeEach
    public void setup() {
        browser = PlaywrightConfig.getBrowser();

        browserContext = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
        page = browserContext.newPage();
    }

    @AfterEach
    public void teardown() {
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
    }
}
