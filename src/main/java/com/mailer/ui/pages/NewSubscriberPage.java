package com.mailer.ui.pages;

import com.mailer.common.AppUrlConfig;
import com.microsoft.playwright.Page;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class NewSubscriberPage extends BaseSMNPage {
    @Getter
    private final String URL = AppUrlConfig.Subscribers.NEW;

    public NewSubscriberPage(Page page) {
        super(page);
    }
}
