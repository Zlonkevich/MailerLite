package com.mailer.ui.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SubscribersPage {
    private final Page page;

    private static final String HOME_TAB = "(//*[@data-test-id='home-tab'])[1]";
    private static final String SUBSCRIBERS_TAB = "(//a[@data-test-id='subscribers-tab'])[1]";
    private static final String FIELDS_TAB = "(//a[@data-test-id='fields-tab'])[1]";
    private static final String ADD_SUBSCRIBER_BUTTON = "[data-test-id='add-subscriber-button']";
    private static final String DELETE_SUBSCRIBER_BUTTON = "[data-test-id='delete-subscriber']";
    private static final String EDIT_SUBSCRIBER_BUTTON = "[data-test-id='edit-subscriber']";

    private void checkButtonAndClick(String selector) {
        if (page.isVisible(selector)) {
            if (page.isEnabled(selector)) {
                page.click(selector);
            } else {
                throw new RuntimeException("Button is disabled: " + selector);
            }
        } else {
            throw new RuntimeException("Button is not visible: " + selector);
        }
    }

    @Step("Navigate to the Home Tab")
    public void goToHomeTab() {
        checkButtonAndClick(HOME_TAB);
    }

    @Step("Navigate to the Subscribers Tab")
    public void goToSubscribersTab() {
        checkButtonAndClick(SUBSCRIBERS_TAB);
    }

    @Step(" Navigate to the Fields Tab")
    public void goToFieldsTab() {
        checkButtonAndClick(FIELDS_TAB);
        return;
    }

    @Step("Click Add Subscriber Button")
    public void clickAddSubscriberButton() {
        checkButtonAndClick(ADD_SUBSCRIBER_BUTTON);
    }

    @Step("Click Edit Subscriber Button (example for a row with subscriber ID or any other field value)")
    public void clickEditSubscriberButton(String subscriberData) {
        String selector = String.format("//td[contains(text(), '%s')]//parent::*//a[@data-test-id='edit-subscriber']", subscriberData);
        checkButtonAndClick(selector);
    }

    @Step("Click Delete Subscriber Button (example for a row with subscriber ID or any other field value)")
    public SubscribersPage clickDeleteSubscriberButton(String subscriberData) {
        String selector = String.format("//td[contains(text(), '%s')]//parent::*//a[@data-test-id='delete-subscriber']", subscriberData);
        checkButtonAndClick(selector);
        return this;
    }

    @Step("Check if a specific subscriber row exists")
    public boolean isSubscriberRowVisible(int subscriberId) {
        String rowSelector = String.format("[data-test-id='subscriber-row'][data-subscriber-id='%d']", subscriberId);
        return page.isVisible(rowSelector);
    }
}
