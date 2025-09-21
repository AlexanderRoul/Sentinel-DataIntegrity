Feature: End-to-end data integrity

  Scenario: Create user via API and verify the UI shows identical data
    Given I have a valid GoREST bearer token
    When I create a unique user via the API
    And I open the contacts page in the UI and search for the user
    Then the UI must show the user with matching name and email
