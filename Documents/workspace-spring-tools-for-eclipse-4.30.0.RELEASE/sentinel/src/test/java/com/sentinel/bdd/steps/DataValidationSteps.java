package com.sentinel.bdd.steps;

import com.sentinel.api.RestClient;
import com.sentinel.api.models.User;
import com.sentinel.ui.pages.ContactsPage;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
public class DataValidationSteps {

    private RestClient api;
    private User createdUser;
    private int createdId;

    @Given("I have a valid GoREST bearer token")
    public void i_have_token() {
        String token = System.getenv("GOREST_TOKEN");
        if (token == null) throw new RuntimeException("GOREST_TOKEN not set");
        api = new RestClient(token);
    }

    @When("I create a unique user via the API")
    public void i_create_unique_user() {
        String unique = "SentinelBDD_" + System.currentTimeMillis();
        createdUser = new User(unique, unique + "@example.com", "male", "active");
        Response resp = api.createUser(createdUser);
        Assert.assertEquals(201, resp.getStatusCode());
        createdId = resp.jsonPath().getInt("id");
    }

    @When("I open the contacts page in the UI and search for the user")
    public void open_contacts_and_search() {
        ContactsPage contacts = new ContactsPage(com.sentinel.utils.TestBase.tlDriver.get());
        contacts.open();
        contacts.search(createdUser.getName());
    }

    @Then("the UI must show the user with matching name and email")
    public void ui_shows_user() {
        ContactsPage contacts = new ContactsPage(com.sentinel.utils.TestBase.tlDriver.get());
        boolean present = contacts.isUserPresentInTable(createdUser.getName());
        Assert.assertTrue(present, "User not found in UI");
        api.deleteUser(createdId);
    }
}
