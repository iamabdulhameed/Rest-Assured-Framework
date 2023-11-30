package com.base;

import com.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class Base {

    public static Map<String, Object> dataFromJsonFile;
    static {
        String env = System.getProperty("env") == null ? "qe" : System.getProperty("env");
        try {
            dataFromJsonFile = JsonUtils.getJsonDataAsMap("reqres/payloads/env/"+env+"/userApiData.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
