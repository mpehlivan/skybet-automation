package com.skybet.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Requests {

    public static Response getRequest(String url) {
        return given().accept(ContentType.JSON).when().get(url);
    }
}
