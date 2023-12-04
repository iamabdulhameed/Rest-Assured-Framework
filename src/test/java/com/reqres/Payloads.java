package com.reqres;

import com.reqres.pojos.ReqRes;
import com.utils.RandomDataGenerator;
import com.utils.RandomDataTypeNames;

import java.util.HashMap;
import java.util.Map;

public class Payloads {

    public static String getCreateUserPayloadFromString(String name, String job) {
        return "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"" + job + "\"\n" +
                "}";
    }

    public static Map<String, Object> getCreateUserPayloadFromMap(String name, String job) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        payload.put("job", job);
        return payload;
    }

    public static Map<String, Object> getCreateUserPayloadFromMap() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", RandomDataGenerator.getRandomData(RandomDataTypeNames.FULLNAME));
        payload.put("job", RandomDataGenerator.getRandomData(RandomDataTypeNames.JOB_POSITION));
        return payload;
    }

    public static ReqRes getCreateUserPayloadFromPojo() {
        return ReqRes
                .builder()
                .name(RandomDataGenerator.getRandomData(RandomDataTypeNames.FULLNAME))
                .job(RandomDataGenerator.getRandomData(RandomDataTypeNames.JOB_TITLE))
                .build();
    }
}
