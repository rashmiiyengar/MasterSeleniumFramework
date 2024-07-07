package org.selenium.pom.utils;

import com.github.javafaker.Faker;

public class FakerUtils {


    public Long generateRandomNumbers(){

        Faker faker = new Faker();
        return faker.number().randomNumber();

    }

    public String generateRandomName(){

        Faker faker = new Faker();
        return faker.name().fullName();

    }
}
