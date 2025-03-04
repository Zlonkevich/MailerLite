package com.mailer.ui;

import com.mailer.common.TestMailerApplication;
import com.mailer.common.dto.SubscriberDTO;
import com.mailer.common.enums.SubscriberStateEnum;
import com.mailer.ui.pages.SubscribersPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestMailerApplication.class)
public class NewSubscriberTest extends BaseUITest {

    @Autowired
    private SubscribersPage subscribersPage;

    @Test
    public void createNewSubscriber() {
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
    }
}
