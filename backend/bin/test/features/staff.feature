Feature: Staff Operations
  As a bank staff member
  I want to manage customer accounts
  So that I can assist customers with their banking needs

  Background:
    Given the banking system is initialized
    And a staff member is logged in with username "staff1"

  Scenario: View customer details
    Given a customer exists with email "john@example.com"
    When the staff searches for customer "john@example.com"
    Then the customer details should be displayed
    And the customer name should be "John Doe"

  Scenario: Approve account opening request
    Given a pending account opening request from "john@example.com"
    When the staff approves the request
    Then the account should be activated
    And the customer should be notified

  Scenario: Reject account opening request
    Given a pending account opening request from "john@example.com"
    When the staff rejects the request with reason "Incomplete documentation"
    Then the request should be marked as rejected
    And the customer should be notified with the reason

  Scenario: Freeze customer account
    Given a customer "john@example.com" has an active account
    When the staff freezes the account with reason "Suspicious activity"
    Then the account status should be "FROZEN"
    And the customer should be notified

  Scenario: Unfreeze customer account
    Given a customer "john@example.com" has a frozen account
    When the staff unfreezes the account
    Then the account status should be "ACTIVE"
    And the customer should be notified

  Scenario: Process loan application
    Given a customer "john@example.com" has a loan application for 10000
    When the staff reviews the application
    And the staff approves the loan with interest rate 5.5
    Then the loan should be disbursed
    And the customer account should be credited with 10000

  Scenario: View all pending requests
    Given there are 3 pending account requests
    And there are 2 pending loan applications
    When the staff views pending requests
    Then 5 total pending items should be displayed