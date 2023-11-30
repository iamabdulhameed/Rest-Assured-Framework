package com.reqres;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class ReqResTests extends ReqResAPIs {

    @Test
    public void createUserTest() throws IOException {
        Map<String, Object> payLoad = Payloads.createUserPayloadFromMap();
        Response response = createUserAPI(payLoad);
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
