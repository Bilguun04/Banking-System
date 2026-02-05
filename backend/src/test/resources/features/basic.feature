Feature: Basic platform health
  As a platform owner
  I want a basic verification scenario
  So that I can confirm Cucumber is wired correctly

  Scenario: Opening accounts
    Given the banking platform is running
    When a customer opens 2 accounts
    Then the platform should report 2 opened accounts
