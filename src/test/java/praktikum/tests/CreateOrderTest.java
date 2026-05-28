package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.client.OrderClient;
import praktikum.model.Order;
import praktikum.utils.BaseTest;

import java.util.List;
import java.util.List;

public class CreateOrderTest extends BaseTest {

    OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Создание заказа с черным ингредиентом")
    public void createOrderWithBlackColorTest() {

        Order order = new Order(List.of(
                "61c0c5a71d1f82001bdaaa6d"
        ));

        orderClient.createOrder(order)
                .statusCode(200);
    }

    @Test
    @DisplayName("Создание заказа с серым ингредиентом")
    public void createOrderWithGreyColorTest() {

        Order order = new Order(List.of(
                "61c0c5a71d1f82001bdaaa72"
        ));

        orderClient.createOrder(order)
                .statusCode(200);
    }

    @Test
    @DisplayName("Создание заказа с двумя ингредиентами")
    public void createOrderWithTwoColorsTest() {

        Order order = new Order(List.of(
                "61c0c5a71d1f82001bdaaa6d",
                "61c0c5a71d1f82001bdaaa72"
        ));

        orderClient.createOrder(order)
                .statusCode(200);
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutColorTest() {

        Order order = new Order(null);

        orderClient.createOrder(order)
                .statusCode(400);
    }
}
