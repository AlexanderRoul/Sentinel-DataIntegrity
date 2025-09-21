package com.sentinel.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiCrudTests {

    private String token;

    @BeforeClass
    public void init() {
        // Get token from environment variable
        token = System.getenv("GOREST_TOKEN");
        
        // Temporary fallback (only for testing, remove later)
        if (token == null || token.isEmpty()) {
            token = "95545429e9d6f89018038743284c8fba4555438e07e0b529ce96e5086e2a2f7e";
        }

        System.out.println("GOREST_TOKEN = " + token);

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Please set GOREST_TOKEN environment variable");
        }

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
    }

    @Test
    public void testCreateReadUpdateDeleteUser() {
        // 1. CREATE USER
        String userPayload = "{ \"name\": \"John Doe\", \"gender\": \"male\", \"email\": \"john.doe" + System.currentTimeMillis() + "@mail.com\", \"status\": \"active\" }";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(userPayload)
                .post("/users");

        System.out.println("CREATE Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 201, "User creation failed");

        int userId = response.jsonPath().getInt("id");

        // 2. READ USER
        Response getResponse = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .get("/users/" + userId);

        Assert.assertEquals(getResponse.statusCode(), 200);

        // 3. UPDATE USER
        String updatePayload = "{ \"name\": \"John Updated\" }";

        Response updateResponse = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(updatePayload)
                .patch("/users/" + userId);

        Assert.assertEquals(updateResponse.statusCode(), 200);

        // 4. DELETE USER
        Response deleteResponse = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .delete("/users/" + userId);

        Assert.assertEquals(deleteResponse.statusCode(), 204);
    }
}
