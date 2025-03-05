package com.mailer.ui.pages;

import com.mailer.common.config.AppUrlConfig;
import com.mailer.common.enums.SubscriberStateEnum;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewSubscriberPage extends BaseSMNPage {
    @Getter
    private final String URL = AppUrlConfig.Subscribers.NEW;

    private Locator nameInput;
    private Locator emailInput;
    private Locator stateSelect;
    private Locator addFieldSelect;
    private Locator createButton;

    private static final String FIELD = "//*[@id='%s']";
    private static final String NAME_REQUIRED = "//p[contains(text(), 'The name field is required.')]";
    private static final String STATE_REQUIRED = "//p[contains(text(), 'The state field is required.')]";
    private static final String EMAIL_MUST_BE_VALID = "//p[contains(text(), 'The email must be a valid email address.')]";
    private static final String VALUE_REQUIRED = "//*[@id='%s']/ancestor::*[3]//p";

    @Autowired
    public NewSubscriberPage(Page page) {
        this.page = page;
        this.nameInput = page.locator("input#name");
        this.emailInput = page.locator("input#email");
        this.stateSelect = page.locator("(//select[@id='state'])[1]");
        this.addFieldSelect = page.locator("(//select[@id='state'])[2]");
        this.createButton = page.locator("//button[@type='submit']");
    }

    @Step("Fill 'Name' input field")
    public NewSubscriberPage fillName(String name) {
        nameInput.fill(name);
        return this;
    }

    @Step("Fill 'Email' input field")
    public NewSubscriberPage fillEmail(String email) {
        emailInput.fill(email);
        return this;
    }

    @Step("Select 'State' from drop down list")
    public NewSubscriberPage selectState(String stateEnum) {
        stateSelect.selectOption(stateEnum);
        return this;
    }

    @Step("Select 'Add Field' from drop down list")
    public NewSubscriberPage selectAddField(String field) {
        addFieldSelect.selectOption(field);
        return this;
    }

    @Step("Select 'Add Field' and input value")
    public NewSubscriberPage selectAddFieldAndInputValue(String fieldName, Object value) {
        addFieldSelect.selectOption(fieldName);

        var fieldLocator = page.locator(String.format(FIELD, fieldName));


        if (value instanceof String) {
            switch (value.toString()) {
                case "Yes", "No" -> fieldLocator.selectOption(value.toString());
                default -> fieldLocator.fill(value.toString());
            }
        } else if (value instanceof Integer) {
            fieldLocator.type(value + "");
        }
        return this;
    }

    @Step("Check if 'The name field is required' message is visible")
    public static boolean isNameRequiredVisible(Page page) {
        return isElementVisible(page, NAME_REQUIRED);
    }

    @Step("Check if 'The state field is required' message is visible")
    public static boolean isStateRequiredVisible(Page page) {
        return isElementVisible(page, STATE_REQUIRED);
    }

    @Step("Check if 'The email must be a valid email address' message is visible")
    public static boolean isEmailMustBeValidVisible(Page page) {
        return isElementVisible(page, EMAIL_MUST_BE_VALID);
    }

    @Step("Check if 'Value required' message with ID {id} is visible")
    public static boolean isValueRequiredVisible(Page page, String id) {
        String formattedXpath = String.format(VALUE_REQUIRED, id);
        return isElementVisible(page, formattedXpath);
    }

    // General method for checking visibility of an element by XPath
    private static boolean isElementVisible(Page page, String xpath) {
        try {
            return page.locator(xpath).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click 'Create' button")
    @SneakyThrows
    public SubscribersPage clickCreateBtn() {
        createButton.click();
        Thread.sleep(1000); // will be replaced with waiting some event
        return new SubscribersPage(page);
    }
}
