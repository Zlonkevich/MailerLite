package com.mailer.ui.pages;

import io.qameta.allure.Step;


public class HomePage {
    private static final String HOME_TAB = "(//*[@data-test-id='home-tab'])[1]";
    private static final String SUBSCRIBERS_TAB = "(//a[@data-test-id='subscribers-tab'])[1]";
    private static final String FIELDS_TAB = "(//a[@data-test-id='fields-tab'])[1]";

    @Step("Click 'Home' tab")
    public HomePage clickHomeTab() {
        return this;
    }


    @Step("Click 'Subscribers' tab")
    public SubscribersPage clickSubscribersTab() {
        return new SubscribersPage()
    }
}
