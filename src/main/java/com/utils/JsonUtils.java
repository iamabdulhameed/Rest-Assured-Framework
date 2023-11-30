package com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> getJsonDataAsMap(String jsonFile) throws IOException {
        String jsonFilePath = System.getProperty("user.dir") + "/src/test/resources/" + jsonFile;
        return objectMapper.readValue(new File(jsonFilePath), new TypeReference<>() {
        });
    }
}
