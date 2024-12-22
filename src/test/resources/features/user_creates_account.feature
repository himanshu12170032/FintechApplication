Feature: User creates an account

  Scenario: User provides valid input
    Given the user has provided a valid account creation request
    When the user submits the account creation request
    Then the account should be created successfully
    And the response should contain the account ID

  Scenario: User provides invalid input
    Given the user has provided an invalid account creation request
    When the user submits the account creation request
    Then the account should not be created
    And the response should contain an error message
