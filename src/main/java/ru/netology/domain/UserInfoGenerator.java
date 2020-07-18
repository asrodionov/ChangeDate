package ru.netology.domain;

import com.github.javafaker.Faker;

import java.util.Locale;

public class UserInfoGenerator {

    public static UserInfo generateUserInfo(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        return new UserInfo(
                "Санкт-Петербург",
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber()
        );
    }
}
