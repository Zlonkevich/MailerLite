package com.mailer.ui.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlaywrightConfig {
    private static Playwright playwright;
    private static Browser browser;

    public static Browser getBrowser() {
        if (playwright == null) {
            playwright = Playwright.create();
        }

        var browserType = System.getProperty("browser", "chrome").toLowerCase();
        var isHeadless = Boolean.getBoolean(System.getProperty("headless", "false").toLowerCase());

        var launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadless);

        switch (browserType) {
            case "chrome", "chromium" -> browser = playwright.chromium().launch(launchOptions);
            case "firefox" -> browser = playwright.firefox().launch(launchOptions);
            case "webkit" -> browser = playwright.webkit().launch(launchOptions);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
        log.info(String.format("Browser %s set", browserType));
        return browser;
    }

    public static void close() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}