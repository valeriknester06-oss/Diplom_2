package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import praktikum.client.UserClient;
import praktikum.model.User;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest extends BaseTest {

    private final UserClient userClient = new UserClient();

    @Test
    @DisplayName("Создание уникального пользователя")
    public void createUniqueUserTest() {
        User user = UserGenerator.getRandomUser();

        ValidatableResponse response = userClient.createUser(user);

        response
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых пользователей")
    public void createDuplicateUserTest() {
        User user = UserGenerator.getRandomUser();

        userClient.createUser(user);

        ValidatableResponse response = userClient.createUser(user);

        response
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Нельзя создать пользователя без email")
    public void createUserWithoutEmailTest() {
        User user = UserGenerator.getUserWithoutEmail();

        ValidatableResponse response = userClient.createUser(user);

        response
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Нельзя создать пользователя без password")
    public void createUserWithoutPasswordTest() {
        User user = UserGenerator.getUserWithoutPassword();

        ValidatableResponse response = userClient.createUser(user);

        response
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Нельзя создать пользователя без name")
    public void createUserWithoutNameTest() {
        User user = UserGenerator.getUserWithoutName();

        ValidatableResponse response = userClient.createUser(user);

        response
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
