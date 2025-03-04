package com.mailer.ui;

import com.mailer.common.RetrofitClient;
import com.mailer.common.TestMailerApplication;
import com.mailer.common.controller.FieldsApi;
import com.mailer.common.dto.CreateFieldDTO;
import com.mailer.common.dto.SubscriberDTO;
import com.mailer.common.enums.FieldTypeEnum;
import com.mailer.common.enums.SubscriberStateEnum;
import com.mailer.ui.pages.SubscribersPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestMailerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("Smoke")
@Epic("Subscribers")
@Story("Creating subscribers")
@DisplayName("Creating subscribers with different fields")
public class NewSubscriberTest extends BaseUITest {
    private static SubscriberDTO subscriber;
    private static FieldsApi api;

    @Autowired
    private SubscribersPage subscribersPage;

    @BeforeAll
    @SneakyThrows
    static void setup() {
        subscriber = new SubscriberDTO()
            .setName("Pit")
            .setEmail("pit@gmail.com")
            .setState(SubscriberStateEnum.ACTIVE);

        api = RetrofitClient.getClient().create(FieldsApi.class);

        // creating a field
        api.createField(new CreateFieldDTO()
                            .setTitle("testTitle")
                            .setType(FieldTypeEnum.STRING.getType())).execute();
    }

    @AfterAll
    public void tearDown() {
        api.deleteField(1);
    }

    @Test
    @Description("Test creates a subscriber and verifies all the fields")
    public void createNewSubscriber() {

        var subs = subscribersPage
            .navigateTo()
            .clickAddSubscriberButton()
            .fillName(subscriber.getName())
            .fillEmail(subscriber.getEmail())
            .selectState(subscriber.getState())
            .selectAddFieldAndInputValue("testTitle", "test value")
            .submitForm();

        assertThat(subs.getSubscriberName("1")).isEqualTo(subscriber.getName());
        assertThat(subs.getSubscriberEmail("1")).isEqualTo(subscriber.getEmail());
        assertThat(subs.getSubscriberState("1")).isEqualTo(subscriber.getState().getState());
        assertThat(subs.getSubscriberAdditionalInfo("1")).isEqualTo("testTitle: test value");
    }
}
