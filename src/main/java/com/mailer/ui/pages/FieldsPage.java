package com.mailer.ui.pages;

import com.mailer.common.utils.ConfigReader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class FieldsPage extends BaseSMNPage {
    protected final Locator addField;
    @Getter
    private final String URL = ConfigReader.getProperty("frontendBaseUrl") + "/fields";

    private static final String ADD_FIELD = "//a[@data-test-id='add-field-button']";

    public FieldsPage(Page page) {
        super(page);
        this.addField = page.locator(ADD_FIELD);
    }

    @Step("Click 'Add field' tab")
    public void clickHomeTab() {
        addField.click();
    }
}
