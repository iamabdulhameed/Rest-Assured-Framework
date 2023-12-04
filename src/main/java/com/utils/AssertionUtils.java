package com.utils;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.reporting.ExtentReportManager;
import com.reporting.Setup;
import io.restassured.response.Response;

import javax.swing.text.html.Option;
import java.util.*;

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
}
