package com.sentinel.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UiDataValidationTest {

    private String token;

    @BeforeClass
    public void setupApiClient() {
        token = System.getenv("GOREST_TOKEN");

        // Temporary fallback for testing
        if (token == null || token.isEmpty()) {
            token = "95545429e9d6f89018038743284c8fba4555438e07e0b529ce96e5086e2a2f7e";
        }

        System.out.println("GOREST_TOKEN = " + token);

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Please set GOREST_TOKEN env var");
        }

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
    }

    @Test
    public void endToEndDataIntegrity() {
        String payload = "{ \"name\": \"Jane Doe\", \"gender\": \"female\", \"email\": \"jane.doe" + System.currentTimeMillis() + "@mail.com\", \"status\": \"active\" }";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .post("/users");

        System.out.println("CREATE Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 201, "User creation failed");

        int userId = response.jsonPath().getInt("id");

        // Additional UI validation steps can be added here
        System.out.println("Created user ID: " + userId);
    }
}
