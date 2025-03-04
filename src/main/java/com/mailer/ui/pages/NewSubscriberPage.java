package com.mailer.ui.pages;

import com.mailer.common.config.AppUrlConfig;
import com.mailer.common.enums.SubscriberStateEnum;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.Getter;
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

    private static final String NAME_FIELD = "input#name";
    private static final String EMAIL_FIELD = "input#email";
    private static final String STATE_SELECT = "(//select[@id='state'])[1]";
    private static final String ADD_FIELD_SELECT = "(//select[@id='state'])[2]";
    private static final String CREATE_BUTTON = "//button[@type='submit']";
    private static final String FIELD = "//*[@id='%s']";


    @Autowired
    public NewSubscriberPage(Page page) {
        this.page = page;
        this.nameInput = page.locator(NAME_FIELD);
        this.emailInput = page.locator(EMAIL_FIELD);
        this.stateSelect = page.locator(STATE_SELECT);
        this.addFieldSelect = page.locator(ADD_FIELD_SELECT);
        this.createButton = page.locator(CREATE_BUTTON);
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
    public NewSubscriberPage selectState(SubscriberStateEnum stateEnum) {
        stateSelect.selectOption(stateEnum.getState());
        return this;
    }

    @Step("Select 'Add Field' from drop down list")
    public NewSubscriberPage selectAddField(String field) {
        addFieldSelect.selectOption(field);
        return this;
    }

    @Step("Select 'Add Field' and input value")
    public NewSubscriberPage selectAddFieldAndInputValue(String fieldName, String value) {
        addFieldSelect.selectOption(fieldName);

        var fieldLocator = page.locator(String.format(FIELD, fieldName));

        var tagName = fieldLocator.evaluate("element => element.tagName").toString().toLowerCase();

        switch (tagName) {
            case "input" -> fieldLocator.fill(value);
            case "select" -> fieldLocator.selectOption(value);
        }

        return this;
    }

    @Step("Click 'Create' button")
    public SubscribersPage submitForm() {
        createButton.click();
        return new SubscribersPage(page);
    }
}
