Feature: User retrieves account details

  Scenario: Authenticated user retrieves account details
    Given the user is authenticated
    And the user has access to the account
    When the user requests account details
    Then the system should return full account details

  Scenario: Unauthenticated user retrieves account details
    Given the user is not authenticated
    When the user requests account details
    Then the system should return limited account details
    And sensitive details should be masked
