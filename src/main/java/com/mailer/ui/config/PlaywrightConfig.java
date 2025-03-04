package com.mailer.ui.config;

import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Slf4j
@Configuration
public class PlaywrightConfig {

    private Playwright playwright;
    private Browser browser;

    @Bean
    public Playwright playwright() {
        playwright = Playwright.create();
        return playwright;
    }

    @Bean
    public Browser browser(Playwright playwright) {
        var browserType = System.getProperty("browser", "chrome").toLowerCase();
        var isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        var launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadless);

        switch (browserType) {
            case "chrome", "chromium" -> browser = playwright.chromium().launch(launchOptions);
            case "firefox" -> browser = playwright.firefox().launch(launchOptions);
            case "webkit" -> browser = playwright.webkit().launch(launchOptions);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }

        log.info("Browser {} set", browserType);
        return browser;
    }

    @Bean
    @Scope("prototype")
    public Page page(Browser browser) {
        return browser.newPage();
    }
}