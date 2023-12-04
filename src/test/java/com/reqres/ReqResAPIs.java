package com.reqres;

import com.base.Base;
import com.reqres.pojos.ReqRes;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.utils.RestUtils.performPostWithoutAuth;

public class ReqResAPIs {

    public Response createUserAPI(Map<String, Object> createUserPayload) {
        String endPoint = (String) Base.dataFromJsonFile.get("endpoint");
        return performPostWithoutAuth(endPoint, createUserPayload, new HashMap<>());
    }

    public Response createUserAPI(Object createUserPayload) {
        String endPoint = (String) Base.dataFromJsonFile.get("endpoint");
        return performPostWithoutAuth(endPoint, createUserPayload, new HashMap<>());
    }
}
