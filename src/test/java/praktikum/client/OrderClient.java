package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String ORDERS = "/api/orders";

    @Step("Создание заказа без авторизации")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .post(ORDERS)
                .then();
    }

    @Step("Создание заказа авторизованным пользователем")
    public ValidatableResponse createOrderAuthorized(Order order, String token) {
        return given()
                .header("Content-type", "application/json")
                .auth()
                .oauth2(token)
                .body(order)
                .post(ORDERS)
                .then();
    }
}