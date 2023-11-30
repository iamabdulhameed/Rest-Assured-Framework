package com.utils;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {

    public static Faker faker = new Faker();

    public static String getRandomData(RandomDataTypeNames dataTypeNames) {
        switch (dataTypeNames) {
            case FIRSTNAME:
                return faker.name().firstName();
            case LASTNAME:
                return faker.name().lastName();
            case FULLNAME:
                return faker.name().fullName();
            case COUNTRY:
                faker.address().country();
            case CITY:
                return faker.address().cityName();
            case STREET_NAME:
                return faker.address().streetName();
            case STREET_ADDRESS:
                return faker.address().streetAddress();
            case ADDRESS:
                return faker.address().fullAddress();
            case ZIP:
                return faker.address().zipCode();
            case JOB_TITLE:
                return faker.job().title();
            case JOB_FIELD:
                return faker.job().field();
            case JOB_POSITION:
                return faker.job().position();

            default:
                return "Data type not found";
        }
    }

    public static String getRandomNumber(int size) {
        return faker.number().digits(size);
    }

    public static String getRandomString(int size) {
        return RandomStringUtils.randomAlphabetic(size);
    }
}
