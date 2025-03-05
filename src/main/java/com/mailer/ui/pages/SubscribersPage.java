package com.mailer.ui.pages;

import com.mailer.common.config.AppUrlConfig;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscribersPage extends BaseSMNPage {
    private Locator addSubscriberButton;
    @Getter
    private final String URL = AppUrlConfig.Subscribers.SUBSCRIBERS;

    private static final String EDIT_BTN = "//td[contains(text(), '%s')]//parent::*//a[@data-test-id='edit-subscriber']";
    private static final String DELETE_BTN = "//td[contains(text(), '%s')]//parent::*//a[@data-test-id='delete-subscriber']";
    private static final String ADD_SUBSCRIBER_BTN = "[data-test-id='add-subscriber-button']";
    private static final String SUBSCRIBER_NAME = "(//tr[@data-test-id='subscriber-row'])[%s]/td[2]";
    private static final String SUBSCRIBER_EMAIL = "(//tr[@data-test-id='subscriber-row'])[%s]/td[3]";
    private static final String SUBSCRIBER_STATE = "(//tr[@data-test-id='subscriber-row'])[%s]/td[4]";
    private static final String SUBSCRIBER_ADDITIONAL_INFO = "(//tr[@data-test-id='subscriber-row'])[%s]/td[5]";

    @Autowired
    public SubscribersPage(Page page) {
        this.page = page;
    }

    @PostConstruct
    private void initLocators() {
        addSubscriberButton = page.locator(ADD_SUBSCRIBER_BTN);
    }

    @Step("Navigate to the page")
    public SubscribersPage navigateTo() {
        page.navigate(URL);
        return this;
    }

    @Step("Click 'Add subscriber' button")
    public NewSubscriberPage clickAddSubscriberButton() {
        addSubscriberButton.click();
        return new NewSubscriberPage(page);
    }

    @Step("Click 'Edit Subscriber Button' chosen by input value")
    public void clickEditButtonByValue(String subscriberData) {
        var editSelector = String.format(EDIT_BTN, subscriberData);
        page.locator(editSelector).click();
    }

    @Step("Click 'Delete Subscriber Button' chosen by input value")
    public void clickDeleteButtonByValue(String subscriberData) {
        var deleteSelector = String.format(DELETE_BTN, subscriberData);
        page.locator(deleteSelector).click();
    }

    @Step("Check if a specific subscriber row exists")
    public boolean isSubscriberRowVisible(int subscriberId) {
        var rowSelector = String.format("[data-test-id='subscriber-row'][data-subscriber-id='%d']", subscriberId);
        return page.isVisible(rowSelector);
    }

    @Step("Getting subscriber's Name")
    public String getSubscriberName(String rowNumber) {
        return page.locator(String.format(SUBSCRIBER_NAME, rowNumber)).textContent();
    }

    @Step("Getting subscriber's Email")
    public String getSubscriberEmail(String rowNumber) {
        return page.locator(String.format(SUBSCRIBER_EMAIL, rowNumber)).textContent();
    }

    @Step("Getting subscriber's State")
    public String getSubscriberState(String rowNumber) {
        return page.locator(String.format(SUBSCRIBER_STATE, rowNumber)).textContent();
    }

    @Step("Getting subscriber's Additional info")
    public String getSubscriberAdditionalInfo(String rowNumber) {
        return page.locator(String.format(SUBSCRIBER_ADDITIONAL_INFO, rowNumber)).textContent();
    }
}
