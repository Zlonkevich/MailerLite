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

    @Step("Click 'Create' button")
    @SneakyThrows
    public SubscribersPage clickCreateBtn() {
        createButton.click();
        Thread.sleep(1000); // will be replaced with waiting some event
        return new SubscribersPage(page);
    }
}
