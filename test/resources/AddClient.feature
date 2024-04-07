Feature: Add Client

  As a bank manager
  I want to be able to add a new client
  So that the client can use our banking services

  Scenario: Successful client creation
    Given I am on the "Add Client" page
    When I enter valid client details
    And I click the "Add Client" button
    Then I should see a confirmation message
    And the new client should be added to the client list

  Scenario: Failed client creation due to missing information
    Given I am on the "Add Client" page
    When I enter incomplete client details
    And I click the "Add Client" button
    Then I should see an error message
    And the new client should not be added to the client list

  Scenario: Failed client creation due to duplicate client
    Given I am on the "Add Client" page
    And a client with the same details already exists
    When I enter the existing client's details
    And I click the "Add Client" button
    Then I should see an error message indicating the client already exists
    And the new client should not be added to the client list