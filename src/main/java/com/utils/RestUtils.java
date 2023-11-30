package com.utils;

import com.reporting.ExtentReportManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Map;

public class RestUtils {

    private static RequestSpecification getRequestSpecification(String endPoint, Object payLoad, Map<String, String> headers) {
        return RestAssured.given()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payLoad);
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("API Endpoint: " + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Request Method: " + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Request Headers: ");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request Body: ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
    }

    private static void printResponseLogInReport(Response response) {
        ExtentReportManager.logInfoDetails("Response Status Code: " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response Headers: ");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Response Body: ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
    }

    public static Response performPostWithoutAuth(String endPoint, String payLoad, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint, payLoad, headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performPostWithoutAuth(String endPoint, Map<String, Object> payLoad, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint, payLoad, headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
}
