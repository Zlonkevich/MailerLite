package com.mailer.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseSMNPage {
    @Autowired
    protected Page page;

    protected Locator homeTab;
    protected Locator subscribersTab;
    protected Locator fieldsTab;

    private static final String HOME_TAB = "(//*[@data-test-id='home-tab'])[1]";
    private static final String FIELDS_TAB = "(//a[@data-test-id='fields-tab'])[1]";
    private static final String SUBSCRIBERS_TAB = "(//a[@data-test-id='subscribers-tab'])[1]";

    @PostConstruct
    public void init() {
        homeTab = page.locator(HOME_TAB);
        subscribersTab = page.locator(SUBSCRIBERS_TAB);
        fieldsTab = page.locator(FIELDS_TAB);
    }

    @Step("Click 'Home' tab")
    public void clickHomeTab() {
        homeTab.click();
    }

    @Step("Click the 'Subscribers' tab")
    public void clickSubscribersTab() {
        subscribersTab.click();
    }

    @Step("Click the 'Fields' tab")
    public void clickFieldsTab() {
        fieldsTab.click();
    }
}
