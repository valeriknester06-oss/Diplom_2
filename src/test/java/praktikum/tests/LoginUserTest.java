package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.UserClient;
import praktikum.model.LoginUser;
import praktikum.model.User;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginUserTest extends BaseTest {

    private final UserClient userClient = new UserClient();
    private User user;

    @Before
    public void setUp() {
        user = UserGenerator.getRandomUser();
    }

    @Test
    @DisplayName("Вход под существующим пользователем")
    @Description("Проверка успешной авторизации существующего пользователя")
    public void loginExistingUserTest() {

        userClient.createUser(user);

        LoginUser loginUser = new LoginUser(
                user.getEmail(),
                user.getPassword()
        );

        ValidatableResponse response = userClient.loginUser(loginUser);

        response
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    @DisplayName("Вход с неверным email")
    @Description("Проверка ошибки авторизации с неверным email")
    public void loginWithIncorrectEmailTest() {

        LoginUser loginUser = new LoginUser(
                "wrong_email@yandex.ru",
                user.getPassword()
        );

        ValidatableResponse response = userClient.loginUser(loginUser);

        response
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Вход с неверным паролем")
    @Description("Проверка ошибки авторизации с неверным паролем")
    public void loginWithIncorrectPasswordTest() {

        userClient.createUser(user);

        LoginUser loginUser = new LoginUser(
                user.getEmail(),
                "wrong_password"
        );

        ValidatableResponse response = userClient.loginUser(loginUser);

        response
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}