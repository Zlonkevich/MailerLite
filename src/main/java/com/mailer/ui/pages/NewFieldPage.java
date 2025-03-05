package com.mailer.ui.pages;

import com.mailer.common.config.AppUrlConfig;
import com.mailer.ui.enums.FieldEnum;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewFieldPage extends BaseSMNPage {
    @Getter
    private final String URL = AppUrlConfig.Fields.NEW;

    private Locator titleInput;
    private Locator typeSelect;
    private Locator createButton;

    @Autowired
    public NewFieldPage(Page page) {
        this.page = page;
        titleInput = page.locator("input#title");
        typeSelect = page.locator("select#type");
        createButton = page.locator("//button[@type='submit']");
    }

    @Step("Fill 'Title' input field")
    public NewFieldPage fillTitle(String title) {
        titleInput.fill(title);
        return this;
    }

    @Step("Select 'Type' from drop down list")
    public NewFieldPage selectType(FieldEnum value) {
        typeSelect.selectOption(value.getType());
        return this;
    }

    @Step("Click 'Create' button")
    @SneakyThrows
    public FieldsPage clickCreateBtn() {
        createButton.click();
        Thread.sleep(200);
        return new FieldsPage(page);
    }
}
