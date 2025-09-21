package com.sentinel.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import com.sentinel.api.models.User;
import static io.restassured.RestAssured.given;
import java.util.Map;

public class RestClient {
    private final String baseUrl = "https://gorest.co.in/public/v2";
    private final String token;

    public RestClient(String token) {
        this.token = token;
        RestAssured.baseURI = baseUrl;
    }

    public Response createUser(User user) {
        return given()
            .contentType(ContentType.JSON)
            .auth().oauth2(token)
            .body(user)
            .when().post("/users")
            .then().extract().response();
    }

    public Response getUser(int id) {
        return given()
            .auth().oauth2(token)
            .when().get("/users/" + id)
            .then().extract().response();
    }

    public Response updateUser(int id, Map<String, Object> body) {
        return given()
            .contentType(ContentType.JSON)
            .auth().oauth2(token)
            .body(body)
            .when().patch("/users/" + id)
            .then().extract().response();
    }

    public Response deleteUser(int id) {
        return given()
            .auth().oauth2(token)
            .when().delete("/users/" + id)
            .then().extract().response();
    }
}
