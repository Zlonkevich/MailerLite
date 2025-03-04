package com.mailer.ui;

import com.mailer.common.TestMailerApplication;
import com.mailer.common.dto.SubscriberDTO;
import com.mailer.common.enums.SubscriberStateEnum;
import com.mailer.ui.pages.SubscribersPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestMailerApplication.class)
public class NewSubscriberTest extends BaseUITest {

    @Autowired
    private SubscribersPage subscribersPage;

    @Test
    @Description("Test creates a subscriber and verifies all the fields")
    public void createNewSubscriber() {
        var subscriber = new SubscriberDTO()
            .setName("Pit")
            .setEmail("pit@gmail.com")
            .setState(SubscriberStateEnum.ACTIVE);

        var subs = subscribersPage
            .navigateTo()
            .clickAddSubscriberButton()
            .fillName(subscriber.getName())
            .fillEmail(subscriber.getEmail())
            .selectState(subscriber.getState())
            .submitForm();

        assertThat(subs.getSubscriberName("1")).isEqualTo(subscriber.getName());
        assertThat(subs.getSubscriberEmail("1")).isEqualTo(subscriber.getEmail());
        assertThat(subs.getSubscriberState("1")).isEqualTo(subscriber.getState().getState());
//        assertThat(subs.getSubscriberAdditionalInfo("1")).isEqualTo(subscriber.getName());
    }
}
