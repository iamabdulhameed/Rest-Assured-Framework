package com.utils;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.reporting.ExtentReportManager;
import com.reporting.Setup;
import io.restassured.response.Response;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AssertionUtils {

    public static void assertJsonPath(Response response, Map<String, Object> expectedValuesMap) {
        List<AssertionKeys> valuesMap = new ArrayList<>();
        valuesMap.add(new AssertionKeys("JSON_PATH", "ACTUAL_VALUE", "EXPECTED_VALUE", "RESULT"));
        boolean allMatched = true;

        Set<String> jsonPaths = expectedValuesMap.keySet();
        for (String jsonPath: jsonPaths) {
            Optional<Object> actualValue = Optional.ofNullable(response.jsonPath().get(jsonPath));
            if (actualValue.isPresent()) {
                Object value = actualValue.get();
                if (value.equals(expectedValuesMap.get(jsonPath))) {
                    valuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "MATCHED"));
                } else {
                    allMatched = false;
                    valuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "NOT_MATCHED"));
                }
            } else {
                allMatched = false;
                valuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), "VALUE_NOT_FOUND", "NOT_MATCHED"));
            }
        }
        if (allMatched) {
            ExtentReportManager.logPassDetails("All assertions are passed!");
        } else {
            ExtentReportManager.logFailureDetails("Assertion failed!");
        }

        String[][] finalAssertionMap = valuesMap.stream().map(assertions -> new String[]{assertions.getJsonPath(), String.valueOf(assertions.getActualValue()), String.valueOf(assertions.getExpectedValue()), assertions.getResult()}).toArray(String[][]::new);
        Setup.extentTestThreadLocal.get().info(MarkupHelper.createTable(finalAssertionMap));
    }

    public static Map<String, Object> getJsonPathsFromPayloadAsMap(Object payLoad) {
        List<String> varNames = Stream.of(payLoad.getClass().getDeclaredFields())
                .map(Field::getName)
                .toList();

        return varNames.stream()
                .collect(Collectors.toMap(key -> key, key -> {
                    try {
                        return invokeGetter(payLoad, payLoad.getClass().getMethod(getMethodName(key)));
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }));
    }

    // Utility method to extract variable name from getter method name
    private static String getMethodName(String getterMethodName) {
        return "get" + getterMethodName.substring(0, 1).toUpperCase() + getterMethodName.substring(1);
    }

    // Utility method to invoke a getter method on an object
    private static Object invokeGetter(Object obj, Method method) {
        try {
            return method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
