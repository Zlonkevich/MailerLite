package com.mailer.ui.pages;

import com.mailer.common.config.AppUrlConfig;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class SubscribersPage extends BaseSMNPage {
    private final Locator addSubscriberButton;
    @Getter
    private final String URL = AppUrlConfig.Subscribers.SUBSCRIBERS;

    private static final String EDIT_BTN = "//td[contains(text(), '%s')]//parent::*//a[@data-test-id='edit-subscriber']";
    private static final String DELETE_BTN = "//td[contains(text(), '%s')]//parent::*//a[@data-test-id='delete-subscriber']";
    private static final String ADD_SUBSCRIBER_BTN = "[data-test-id='add-subscriber-button']";

    public SubscribersPage(Page page) {
        super(page);
        this.addSubscriberButton = page.locator(ADD_SUBSCRIBER_BTN);
    }

    @Step("Click 'Add subscriber' button")
    public void clickAddSubscriberButton() {
        addSubscriberButton.click();
    }

    @Step("Click 'Edit Subscriber Button' chosen by input value")
    public void clickEditButtonByValue(String subscriberData) {
        var editSelector = String.format(EDIT_BTN, subscriberData);
        page.locator(editSelector).click();
    }

    @Step("Click 'Delete Subscriber Button' chosen by input value")
    public void clickDeleteButtonByValue(String subscriberData) {
        String deleteSelector = String.format(DELETE_BTN, subscriberData);
        page.locator(deleteSelector).click();
    }

    @Step("Check if a specific subscriber row exists")
    public boolean isSubscriberRowVisible(int subscriberId) {
        String rowSelector = String.format("[data-test-id='subscriber-row'][data-subscriber-id='%d']", subscriberId);
        return page.isVisible(rowSelector);
    }
}
