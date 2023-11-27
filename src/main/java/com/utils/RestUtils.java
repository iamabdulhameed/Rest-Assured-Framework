package com.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {

    public static Response performPostWithoutAuth(String endPoint, String payLoad, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payLoad)
                .post().
                then().log().all().extract().response();
    }

    public static Response performPostWithoutAuth(String endPoint, Map<String, Object> payLoad, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payLoad)
                .post().
                then().log().all().extract().response();
    }
}
