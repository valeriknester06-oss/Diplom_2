package praktikum.utils;

import praktikum.model.User;

public class UserGenerator {

    public static User getRandomUser() {
        int random = (int) (Math.random() * 1000000);

        return new User(
                "test" + random + "@yandex.ru",
                "password123",
                "TestUser"
        );
    }

    public static User getUserWithoutEmail() {
        return new User(
                null,
                "password123",
                "TestUser"
        );
    }

    public static User getUserWithoutPassword() {
        return new User(
                "test" + System.currentTimeMillis() + "@yandex.ru",
                null,
                "TestUser"
        );
    }

    public static User getUserWithoutName() {
        return new User(
                "test" + System.currentTimeMillis() + "@yandex.ru",
                "password123",
                null
        );
    }
}
