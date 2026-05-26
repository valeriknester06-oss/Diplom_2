package praktikum.client;

import io.restassured.response.ValidatableResponse;
import praktikum.model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String ORDERS = "/api/orders";

    public ValidatableResponse createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .post(ORDERS)
                .then();
    }

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