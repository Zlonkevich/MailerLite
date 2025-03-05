package com.mailer.ui;

import com.mailer.common.RetrofitClient;
import com.mailer.common.TestMailerApplication;
import com.mailer.common.controller.FieldsApi;
import com.mailer.common.dto.CreateFieldDTO;
import com.mailer.common.dto.SubscriberDTO;
import com.mailer.common.enums.SubscriberStateEnum;
import com.mailer.ui.enums.FieldEnum;
import com.mailer.ui.pages.FieldsPage;
import com.mailer.ui.pages.SubscribersPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestMailerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("E2E")
@Epic("Subscribers")
@Story("Creating subscribers")
@DisplayName("Creating subscribers with different fields")
@Slf4j
public class NewSubscriberTest extends BaseUITest {
    private SubscriberDTO subscriber;
    private int fieldId;

    private static FieldsApi fieldsApi;
    private static final String TEST_TITLE = "testTitle";

    @Autowired
    private SubscribersPage subscribersPage;

    @Autowired
    private FieldsPage fieldsPage;

    @BeforeAll
    static void init() {
        fieldsApi = RetrofitClient.getClient().create(FieldsApi.class);
    }

    @BeforeEach
    @SneakyThrows
    void setup() {
        subscriber = new SubscriberDTO()
            .setName("Pit")
            .setEmail("pit@gmail.com")
            .setState(SubscriberStateEnum.ACTIVE);
    }

    @AfterEach
    void clearEntities() {
    }

    @AfterAll
    public void tearDown() {
    }

    //    @Test
    @Description("Test creates a subscriber and verifies all the fields")
    @SneakyThrows
    public void createNewSubscriber() {
        var value = "test value";

        fieldsApi.createField(new CreateFieldDTO()
                                  .setTitle(TEST_TITLE)
                                  .setType(FieldEnum.STRING.getType()))
            .execute()
            .body().data().id();


        var subs = subscribersPage
            .navigateTo()
            .clickAddSubscriberButton()
            .fillName(subscriber.getName())
            .fillEmail(subscriber.getEmail())
            .selectState(subscriber.getState())
            .selectAddFieldAndInputValue(TEST_TITLE, value)
            .clickCreateBtn();

        assertThat(subs.getSubscriberName("1")).isEqualTo(subscriber.getName());
        assertThat(subs.getSubscriberEmail("1")).isEqualTo(subscriber.getEmail());
        assertThat(subs.getSubscriberState("1")).isEqualTo(subscriber.getState().getState());
        assertThat(subs.getSubscriberAdditionalInfo("1")).isEqualTo(String.format("%s: %s", TEST_TITLE, value));
    }

    @Test
    @Description("Field types restrictions are working correctly")
    public void fieldTypesRestrictions() {
        // it would be way faster to use the API to create all field types. Depends on test purposes
        /**
         FieldEnum.stream().forEach(f -> {
         try {
         fieldsApi.createField(new CreateFieldDTO()
         .setTitle(f.getType())
         .setType(f.getType()))
         .execute();
         } catch (IOException e) {
         throw new RuntimeException(e);
         }
         });
         */

        fieldsPage
            .navigateTo()
            .clickAddFieldBtn()
            .fillTitle(FieldEnum.STRING.getType())
            .selectType(FieldEnum.STRING)
            .clickCreateBtn()

            .clickAddFieldBtn()
            .fillTitle(FieldEnum.DATE.getType())
            .selectType(FieldEnum.DATE)
            .clickCreateBtn()

            .clickAddFieldBtn()
            .fillTitle(FieldEnum.BOOLEAN.getType())
            .selectType(FieldEnum.BOOLEAN)
            .clickCreateBtn()

            .clickAddFieldBtn()
            .fillTitle(FieldEnum.NUMBER.getType())
            .selectType(FieldEnum.NUMBER)
            .clickCreateBtn();

        subscribersPage
            .navigateTo()
            .clickAddSubscriberButton()
            .fillName(subscriber.getName())
            .fillEmail(subscriber.getEmail())
            .selectState(subscriber.getState())
            .selectAddFieldAndInputValue(FieldEnum.STRING.getType(), FieldEnum.STRING.getType())
            .selectAddFieldAndInputValue(FieldEnum.NUMBER.getType(), 1)
            .selectAddFieldAndInputValue(FieldEnum.DATE.getType(), 10102020)
            .selectAddFieldAndInputValue(FieldEnum.BOOLEAN.getType(), "Yes")
            .clickCreateBtn();

    }
}
