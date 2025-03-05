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
public class FieldsPage extends BaseSMNPage {
    @Getter
    private final String URL = AppUrlConfig.Fields.FIELDS;
    protected Locator addField;

    private static final String ADD_FIELD = "//a[@data-test-id='add-field-button']";

    @Autowired
    public FieldsPage(Page page) {
        this.page = page;
        this.addField = page.locator(ADD_FIELD);
    }

    @Step("Navigate to the page")
    public FieldsPage navigateTo() {
        page.navigate(URL);
        return this;
    }

    @PostConstruct
    private void initLocators() {
        addField = page.locator(ADD_FIELD);
    }

    @Step("Click 'Add field' button")
    public NewFieldPage clickAddFieldBtn() {
        addField.click();
        return new NewFieldPage(page);
    }
}
