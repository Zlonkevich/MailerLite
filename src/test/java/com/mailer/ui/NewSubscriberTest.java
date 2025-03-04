package com.mailer.ui;

import com.mailer.common.dto.SubscriberDTO;
import com.mailer.common.enums.SubscriberStateEnum;
import com.mailer.ui.pages.FieldsPage;
import com.mailer.ui.pages.HomePage;
import com.mailer.ui.pages.NewFieldPage;
import com.mailer.ui.pages.SubscribersPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NewSubscriberTest extends BaseUITest {
    @Autowired
    private FieldsPage fieldsPage;

    @Autowired
    private NewFieldPage newFieldPage;

    @Autowired
    private SubscribersPage subscribersPage;

    @Autowired
    private HomePage homePage;


    @Test
    public void createNewSubscriber() {
//        var subscribersPage = new SubscribersPage(page);

        var subscriber = new SubscriberDTO()
            .setName("Pit")
            .setEmail("pit@gmail.com")
            .setState(SubscriberStateEnum.ACTIVE);

        subscribersPage
            .navigateTo()
            .clickAddSubscriberButton()
            .fillName(subscriber.getName())
            .fillEmail(subscriber.getEmail())
            .selectState(subscriber.getState())
            .submitForm();

        System.out.println("finished");
    }
}
