package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.model.LoginUser;
import praktikum.model.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String REGISTER = "/api/auth/register";
    private static final String LOGIN = "/api/auth/login";

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(REGISTER)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(LoginUser loginUser) {
        return given()
                .header("Content-type", "application/json")
                .body(loginUser)
                .post(LOGIN)
                .then();
    }
}