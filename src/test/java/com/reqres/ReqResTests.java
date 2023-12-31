package com.reqres;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reqres.pojos.ReqRes;
import com.reqres.pojos.ReqResWithDefaultValues;
import com.utils.AssertionUtils;
import com.utils.ExcelUtils;
import com.utils.RandomDataGenerator;
import com.utils.RandomDataTypeNames;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.utils.ExcelUtils.getExcelDataAsListOfMap;

public class ReqResTests extends ReqResAPIs {

    @Test
    public void createUserTest() throws IOException {
        ReqRes payLoad = Payloads.getCreateUserPayloadFromPojo();
        Response response = createUserAPI(payLoad);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void createUserWithDefaultValuesTest() throws IOException {
        //ReqResWithDefaultValues payLoad = new ReqResWithDefaultValues(); // Now it will work with default values
        // Trying with builder method
        //ReqResWithDefaultValues payLoad = ReqResWithDefaultValues.builder().name(RandomDataGenerator.getRandomData(RandomDataTypeNames.FULLNAME)).build();
        /**
         * Above builder will not work because if we want to change a single value from the given default value then it will
         * simply override the other default values to null for string, 0 for int and false for booleans.
         * so to avoid it we can use toBuilder method that only changes the value we want to modify
         * and rest of the values will come from default given values. also refer Pojo for more details.
         * Look at below code for toBuilder way
         */
        ReqResWithDefaultValues payLoad = new ReqResWithDefaultValues().toBuilder().name("SuperMan").build();
        Response response = createUserAPI(payLoad);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void createUserTestAndVerifyResponse() throws IOException {
        ReqRes payLoad = Payloads.getCreateUserPayloadFromPojo();
        Response response = createUserAPI(payLoad);

        // First way using JSON Path for all fields (hectic work :D)
        Assert.assertEquals(response.jsonPath().get("name"), payLoad.getName());

        // Second way using POJO classes to check the values passed during request is coming same in response
        ObjectMapper objectMapper = new ObjectMapper();
        ReqRes createUserResponse = objectMapper.readValue(response.getBody().asString(), ReqRes.class);
        Assert.assertEquals(createUserResponse, payLoad);
    }

    @Test
    public void createUserTestAndVerifyResponseWithLogging() throws IOException {
        ReqRes payLoad = Payloads.getCreateUserPayloadFromPojo();
        Response response = createUserAPI(payLoad);

        Map<String, Object> valuesMap = AssertionUtils.getJsonPathsFromPayloadAsMap(payLoad);
        AssertionUtils.assertJsonPath(response, valuesMap);
    }

    @Test(dataProvider = "CreateUserAPIData")
    public void createUserTestWithDataProvider(ReqRes reqResPayload) {
        Response response = createUserAPI(reqResPayload);

        Map<String, Object> valuesMap = AssertionUtils.getJsonPathsFromPayloadAsMap(reqResPayload);
        AssertionUtils.assertJsonPath(response, valuesMap);
    }

    @DataProvider(name = "CreateUserAPIData")
    public Iterator<ReqRes> getCreateUserAPIDataFromExcel() throws IOException {
        List<LinkedHashMap<String, String>> excelListOfMaps = ExcelUtils.getExcelDataAsListOfMap("API_Test_Data", "Sheet1");
        List<ReqRes> payloads = new ArrayList<>();
        for (LinkedHashMap<String, String> data: excelListOfMaps) {
            ReqRes payload = ReqRes.builder()
                    .name(data.get("name"))
                    .job(data.get("job"))
                    .build();
            payloads.add(payload);
        }

        return payloads.iterator();
    }
}
