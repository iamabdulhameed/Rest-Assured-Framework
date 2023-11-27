package com.reqres;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.utils.RestUtils.*;

public class ReqResTests {

    @Test
    public void createUser() {

        String endPoint = "https://reqres.in/api/users";
        Map<String, Object> payLoad = Payloads.createUserPayloadFromMap("Batman", "Superhero");

        Response response = performPostWithoutAuth(endPoint, payLoad, new HashMap<>());

        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
