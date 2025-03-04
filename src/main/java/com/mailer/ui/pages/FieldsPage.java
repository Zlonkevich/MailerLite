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
    protected Locator addField;
    @Getter
    private final String URL = AppUrlConfig.Fields.FIELDS;

    private static final String ADD_FIELD = "//a[@data-test-id='add-field-button']";

    @Autowired
    public FieldsPage(Page page) {
        this.page = page;
    }

    @PostConstruct
    private void initLocators() {
        addField = page.locator(ADD_FIELD);
    }

    @Step("Click 'Add field' tab")
    public void clickHomeTab() {
        addField.click();
    }
}
