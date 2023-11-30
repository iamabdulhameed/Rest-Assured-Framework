package com.reqres;

import com.utils.RandomDataGenerator;
import com.utils.RandomDataTypeNames;

import java.util.HashMap;
import java.util.Map;

public class Payloads {

    public static String createUserPayloadFromString(String name, String job) {
        return "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"" + job + "\"\n" +
                "}";
    }

    public static Map<String, Object> createUserPayloadFromMap(String name, String job) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        payload.put("job", job);
        return payload;
    }

    public static Map<String, Object> createUserPayloadFromMap() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", RandomDataGenerator.getRandomData(RandomDataTypeNames.FULLNAME));
        payload.put("job", RandomDataGenerator.getRandomData(RandomDataTypeNames.JOB_POSITION));
        return payload;
    }
}
