package com.mailer.ui.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class PlaywrightCleanup implements DisposableBean {

    private final Browser browser;
    private final Playwright playwright;

    public PlaywrightCleanup(Browser browser, Playwright playwright) {
        this.browser = browser;
        this.playwright = playwright;
    }

    @Override
    public void destroy() {
        if (browser != null) {
            browser.close();
            System.out.println("Browser closed");
        }
        if (playwright != null) {
            playwright.close();
            System.out.println("Playwright closed");
        }
    }
}
