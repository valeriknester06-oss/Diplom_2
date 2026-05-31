package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.client.OrderClient;
import praktikum.model.Order;
import praktikum.utils.BaseTest;

import java.util.List;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;

public class CreateOrderTest extends BaseTest {

    private final OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Создание заказа с черным ингредиентом")
    @Description("Проверка успешного создания заказа с одним ингредиентом")
    public void createOrderWithBlackIngredientTest() {

        Order order = new Order(List.of(
                "61c0c5a71d1f82001bdaaa6d"
        ));

        orderClient.createOrder(order)
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Создание заказа с белым ингредиентом")
    @Description("Проверка успешного создания заказа с одним ингредиентом")
    public void createOrderWithWhiteIngredientTest() {

        Order order = new Order(List.of(
                "61c0c5a71d1f82001bdaaa72"
        ));

        orderClient.createOrder(order)
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Создание заказа с двумя ингредиентами")
    @Description("Проверка успешного создания заказа с двумя ингредиентами")
    public void createOrderWithTwoIngredientsTest() {

        Order order = new Order(List.of(
                "61c0c5a71d1f82001bdaaa6d",
                "61c0c5a71d1f82001bdaaa72"
        ));

        orderClient.createOrder(order)
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Проверка ошибки при создании заказа без ингредиентов")
    public void createOrderWithoutIngredientsTest() {

        Order order = new Order(null);

        orderClient.createOrder(order)
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Создание заказа с неверным хэшем ингредиента")
    @Description("Проверка ошибки при создании заказа с неверным хэшем ингредиента")
    public void createOrderWithIncorrectIngredientHashTest() {

        Order order = new Order(List.of(
                "incorrect_hash"
        ));

        orderClient.createOrder(order)
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}