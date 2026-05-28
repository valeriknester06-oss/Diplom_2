package praktikum.utils;

import praktikum.model.Order;

import java.util.List;

public class OrderGenerator {

    public static Order getOrderWithColor(List<String> colors) {
        return new Order(colors);
    }
}
