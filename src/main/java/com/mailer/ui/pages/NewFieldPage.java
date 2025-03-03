package com.mailer.ui.pages;

import com.mailer.common.utils.ConfigReader;
import com.mailer.ui.enums.FieldEnum;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class NewFieldPage extends BaseSMNPage {
    @Getter
    private final String URL = ConfigReader.getProperty("frontendBaseUrl") + "/fields/new";

    private final Locator titleInput;
    private final Locator typeSelect;
    private final Locator createButton;

    private static final String TITLE_FIELD = "input#title";
    private static final String TYPE_DD_LIST = "select#type";
    private static final String CREATE_BUTTON = "//button[@type='submit']";

    public NewFieldPage(Page page) {
        super(page);
        this.titleInput = page.locator(TITLE_FIELD);
        this.typeSelect = page.locator(TYPE_DD_LIST);
        this.createButton = page.locator(CREATE_BUTTON);
    }

    @Step("Fill 'Title' input field")
    public void fillTitle(String title) {
        titleInput.fill(title);
    }

    @Step("Select 'Type' from drop down list")
    public void selectType(FieldEnum value) {
        typeSelect.selectOption(value.getType());
    }

    @Step("Click 'Create' button")
    public void submitForm() {
        createButton.click();
    }
}
