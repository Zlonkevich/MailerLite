package com.mailer.ui.pages;

import com.mailer.common.config.AppUrlConfig;
import com.mailer.common.enums.SubscriberStateEnum;
import com.mailer.ui.enums.FieldEnum;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class NewSubscriberPage extends BaseSMNPage {
    @Getter
    private final String URL = AppUrlConfig.Subscribers.NEW;

    private final Locator nameInput;
    private final Locator emailInput;
    private final Locator stateSelect;
    private final Locator addFieldSelect;
    private final Locator createButton;

    private static final String NAME_FIELD = "input#name";
    private static final String EMAIL_FIELD = "input#email";
    private static final String STATE_SELECT = "#state";
    private static final String ADD_FIELD_SELECT = "#state";
    private static final String CREATE_BUTTON = "//button[@type='submit']";


    public NewSubscriberPage(Page page) {
        super(page);
        this.nameInput = page.locator(NAME_FIELD);
        this.emailInput = page.locator(EMAIL_FIELD);
        this.stateSelect = page.locator(STATE_SELECT).nth(1);
        this.addFieldSelect = page.locator(ADD_FIELD_SELECT).nth(2);
        this.createButton = page.locator(CREATE_BUTTON);
    }

    @Step("Fill 'Name' input field")
    public void fillName(String name) {
        nameInput.fill(name);
    }

    @Step("Fill 'Email' input field")
    public void fillEmail(String email) {
        emailInput.fill(email);
    }

    @Step("Select 'State' from drop down list")
    public void selectState(SubscriberStateEnum stateEnum) {
        addFieldSelect.selectOption(stateEnum.getState());
    }

    @Step("Click 'Create' button")
    public void submitForm() {
        createButton.click();
    }
}
