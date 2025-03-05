package com.mailer.ui;

import com.mailer.common.RetrofitClient;
import com.mailer.common.TestMailerApplication;
import com.mailer.common.controller.FieldsApi;
import com.mailer.common.controller.SubscribersApi;
import com.mailer.common.dto.CreateFieldDTO;
import com.mailer.common.dto.SubscriberDTO;
import com.mailer.common.dto.subscribers.GetSubscribersRec;
import com.mailer.common.enums.SubscriberStateEnum;
import com.mailer.ui.enums.FieldEnum;
import com.mailer.ui.pages.FieldsPage;
import com.mailer.ui.pages.NewSubscriberPage;
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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestMailerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("E2E")
@Epic("Subscribers")
@Story("Creating subscribers")
@DisplayName("Creating subscribers with different fields")
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubscribersTest extends BaseUITest {
    private List<SubscriberDTO> subscribers;
    private GetSubscribersRec getSubscribersRec;
    private int fieldId;

    private static FieldsApi fieldsApi;
    private static SubscribersApi subscribersApi;
    private static final String TEST_TITLE = "testTitle";

    @Autowired
    private SubscribersPage subscribersPage;

    @Autowired
    private FieldsPage fieldsPage;

    @BeforeAll
    static void init() {
        fieldsApi = RetrofitClient.getClient().create(FieldsApi.class);
        subscribersApi = RetrofitClient.getClient().create(SubscribersApi.class);
    }

    @BeforeEach
    @SneakyThrows
    void setup() {
        subscribers = List.of(new SubscriberDTO()
                                  .setName("Pit")
                                  .setEmail("pit@gmail.com")
                                  .setState(SubscriberStateEnum.ACTIVE),
                              new SubscriberDTO()
                                  .setName("Tom")
                                  .setEmail("tom@gmail.com")
                                  .setState(SubscriberStateEnum.BOUNCED));
    }

    @AfterEach
    void clearEntities() {
    }

    @AfterAll
    public void tearDown() {
    }

    @Test
    @Order(1)
    @Description("Test creates a subscriber and verifies all his fields")
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
            .fillName(subscribers.get(0).getName())
            .fillEmail(subscribers.get(0).getEmail())
            .selectState(subscribers.get(0).getState().getState())
            .selectAddFieldAndInputValue(TEST_TITLE, value)
            .clickCreateBtn();

        assertThat(subs.getSubscriberName("1")).isEqualTo(subscribers.get(0).getName());
        assertThat(subs.getSubscriberEmail("1")).isEqualTo(subscribers.get(0).getEmail());
        assertThat(subs.getSubscriberState("1")).isEqualTo(subscribers.get(0).getState().getState());
        assertThat(subs.getSubscriberAdditionalInfo("1")).isEqualTo(String.format("%s: %s", TEST_TITLE, value));
    }

    @Test
    @Order(2)
    @Description("Field types restrictions are working correctly")
    public void fieldTypesRestrictions() {
        // it would be way faster to use the API to create all field types like this:
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

        // setting incorrect values
        var newSubs = subscribersPage
            .navigateTo()
            .clickAddSubscriberButton()
            .fillName("")
            .fillEmail("asdasd@asd")
            .selectState("")
            .selectAddFieldAndInputValue(FieldEnum.STRING.getType(), "")
            .selectAddField(FieldEnum.BOOLEAN.getType())
            .selectAddFieldAndInputValue(FieldEnum.NUMBER.getType(), "")
            .selectAddFieldAndInputValue(FieldEnum.DATE.getType(), "");

        newSubs.clickCreateBtn();

        // verifying warnings
        assertTrue(NewSubscriberPage.isEmailMustBeValidVisible(page));
        assertTrue(NewSubscriberPage.isNameRequiredVisible(page));
        assertTrue(NewSubscriberPage.isStateRequiredVisible(page));
        assertTrue(NewSubscriberPage.isValueRequiredVisible(page, FieldEnum.STRING.getType()));
        assertTrue(NewSubscriberPage.isValueRequiredVisible(page, FieldEnum.NUMBER.getType()));
        assertTrue(NewSubscriberPage.isValueRequiredVisible(page, FieldEnum.DATE.getType()));
        assertTrue(NewSubscriberPage.isValueRequiredVisible(page, FieldEnum.BOOLEAN.getType()));


        // setting correct values
        var subs = newSubs.
            fillName(subscribers.get(1).getName())
            .fillEmail(subscribers.get(1).getEmail())
            .selectState(subscribers.get(1).getState().getState())
            .selectAddFieldAndInputValue(FieldEnum.STRING.getType(), FieldEnum.STRING.getType())
            .selectAddFieldAndInputValue(FieldEnum.NUMBER.getType(), 1)
            .selectAddFieldAndInputValue(FieldEnum.DATE.getType(), 10102020)
            .selectAddFieldAndInputValue(FieldEnum.BOOLEAN.getType(), "Yes")
            .clickCreateBtn();

        // verifying correct values
        assertThat(subs.getSubscriberName("1")).isEqualTo(subscribers.get(1).getName());
        assertThat(subs.getSubscriberEmail("1")).isEqualTo(subscribers.get(1).getEmail());
        assertThat(subs.getSubscriberState("1")).isEqualTo(subscribers.get(1).getState().getState());
        assertThat(subs.getSubscriberAdditionalInfo("1")).contains(String.format("%s: %s", FieldEnum.STRING.getType(), FieldEnum.STRING.getType()));
        assertThat(subs.getSubscriberAdditionalInfo("1")).contains(String.format("%s: %s", FieldEnum.NUMBER.getType(), 1));
        assertThat(subs.getSubscriberAdditionalInfo("1")).contains(String.format("%s: %s", FieldEnum.DATE.getType(), "2020-10-10"));
        assertThat(subs.getSubscriberAdditionalInfo("1")).contains(String.format("%s: %s", FieldEnum.BOOLEAN.getType(), 1));

    }

    @Test
    @Order(3)
    @Description("Response is not empty")
    void testResponseDataCount() throws Exception {
        Call<GetSubscribersRec> call = subscribersApi.getFields();
        GetSubscribersRec response = call.execute().body();

        assertNotNull(response);
        assertNotNull(response.data());
        assertFalse(response.data().isEmpty(), "Expected non-empty data list");
    }

    @Test
    @Order(4)
    @Description("User's fields check ")
    void testUserDataFields() throws Exception {
        Call<GetSubscribersRec> call = subscribersApi.getFields();
        GetSubscribersRec response = call.execute().body();

        assertNotNull(response);
        assertNotNull(response.data());

        var user = response.data().get(0);
        assertTrue(user.id() > 0);
        assertEquals("tom@gmail.com", user.email());
        assertEquals("Tom", user.name());
        assertEquals("bounced", user.state());
    }

    @Test
    @Order(5)
    @Description("Fields count")
    void testFieldsCount() throws Exception {
        Call<GetSubscribersRec> call = subscribersApi.getFields();
        GetSubscribersRec response = call.execute().body();

        assertNotNull(response);
        assertNotNull(response.data());

        var user = response.data().get(0);
        assertTrue(user.fields().size() > 0, "Expected fields for user");

        var field = user.fields().get(0);
        assertTrue(field.fieldId() > 0);
        assertEquals("string", field.title());
        assertEquals("string", field.type());
        assertEquals("string", field.value());
    }

    @Test
    @Order(6)
    @Description("Multiple user check")
    void testMultipleUsers() throws Exception {
        Call<GetSubscribersRec> call = subscribersApi.getFields();
        GetSubscribersRec response = call.execute().body();

        assertNotNull(response);
        assertNotNull(response.data());

        var secondUser = response.data().get(1);
        assertTrue(secondUser.id() > 0);
        assertEquals("pit@gmail.com", secondUser.email());
        assertEquals("Pit", secondUser.name());
        assertEquals("active", secondUser.state());
    }
}
