package com.mailer.ui.config;

import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PlaywrightConfig {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;

    @Bean(destroyMethod = "close")
    public Playwright playwright() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        return playwright;
    }

    @Bean(destroyMethod = "close")
    public Browser browser(Playwright playwright) {
        if (browser == null) {
            var browserType = System.getProperty("browser", "chrome").toLowerCase();
            var isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

            var launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadless);

            switch (browserType) {
                case "chrome", "chromium" -> browser = playwright.chromium().launch(launchOptions);
                case "firefox" -> browser = playwright.firefox().launch(launchOptions);
                case "webkit" -> browser = playwright.webkit().launch(launchOptions);
                default -> throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }
            log.info("Browser {} launched", browserType);
        }
        return browser;
    }

    @Bean
    public Page page(BrowserContext browserContext) {
        return browserContext.newPage();
    }

    @Bean(destroyMethod = "close")
    public BrowserContext browserContext(Browser browser) {
        if (browserContext == null) {
            browserContext = browser.newContext();
            log.info("Browser context created.");
        }
        return browserContext;
    }
}