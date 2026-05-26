package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import praktikum.client.UserClient;
import praktikum.model.LoginUser;
import praktikum.model.User;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginUserTest extends BaseTest {

    private final UserClient userClient = new UserClient();

    @Test
    @DisplayName("Вход под существующим пользователем")
    public void loginExistingUserTest() {
        User user = UserGenerator.getRandomUser();

        userClient.createUser(user);

        LoginUser loginUser = new LoginUser(user.getEmail(), user.getPassword());

        ValidatableResponse response = userClient.loginUser(loginUser);

        response
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    @DisplayName("Вход с неверным логином и паролем")
    public void loginWithIncorrectEmailAndPasswordTest() {
        LoginUser loginUser = new LoginUser(
                "wrong_email@yandex.ru",
                "wrong_password"
        );

        ValidatableResponse response = userClient.loginUser(loginUser);

        response
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}