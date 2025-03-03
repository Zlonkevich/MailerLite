package com.mailer.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

@Component
public class BaseSMNPage {
    protected final Page page;

    protected final Locator homeTab;
    protected final Locator subscribersTab;
    protected final Locator fieldsTab;

    private static final String HOME_TAB = "(//*[@data-test-id='home-tab'])[1]";
    private static final String FIELDS_TAB = "(//a[@data-test-id='fields-tab'])[1]";
    private static final String SUBSCRIBERS_TAB = "(//a[@data-test-id='subscribers-tab'])[1]";

    public BaseSMNPage(Page page) {
        this.page = page;
        this.homeTab = page.locator(HOME_TAB);
        this.subscribersTab = page.locator(SUBSCRIBERS_TAB);
        this.fieldsTab = page.locator(FIELDS_TAB);
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
