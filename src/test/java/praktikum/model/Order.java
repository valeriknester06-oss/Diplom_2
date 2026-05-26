package praktikum.model;

import java.util.List;

public class Order {

    private List<String> ingredients;
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;

    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
        this.firstName = "Валерия";
        this.lastName = "Тест";
        this.address = "Москва";
        this.metroStation = 4;
        this.phone = "+79999999999";
        this.rentTime = 5;
        this.deliveryDate = "2026-06-01";
        this.comment = "Тестовый заказ";
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }
}