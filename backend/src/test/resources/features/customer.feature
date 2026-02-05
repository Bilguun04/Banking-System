Feature: Customer Operations
  As a bank customer
  I want to manage my accounts
  So that I can perform banking transactions

  Background:
    Given the banking system is initialized

  Scenario: Customer registration
    Given a new customer with name "John Doe" and email "john@example.com"
    When the customer registers with password "SecurePass123"
    Then the registration should be successful
    And the customer should receive a welcome notification

  Scenario: Customer login
    Given a registered customer with email "john@example.com" and password "SecurePass123"
    When the customer attempts to login
    Then the login should be successful
    And the customer should receive an access token

  Scenario: Failed login with wrong password
    Given a registered customer with email "john@example.com" and password "SecurePass123"
    When the customer attempts to login with password "WrongPass"
    Then the login should fail
    And the error message should be "Invalid credentials"

  Scenario: Open a new account
    Given a logged in customer "john@example.com"
    When the customer opens a "SAVINGS" account
    Then a new account should be created
    And the account balance should be 0

  Scenario: Deposit money
    Given a logged in customer "john@example.com"
    And the customer has a "SAVINGS" account with balance 100
    When the customer deposits 500
    Then the account balance should be 600
    And a transaction record should be created

  Scenario: Withdraw money
    Given a logged in customer "john@example.com"
    And the customer has a "SAVINGS" account with balance 1000
    When the customer withdraws 300
    Then the account balance should be 700
    And a transaction record should be created

  Scenario: Withdraw more than balance
    Given a logged in customer "john@example.com"
    And the customer has a "SAVINGS" account with balance 100
    When the customer withdraws 500
    Then the withdrawal should fail
    And the error message should be "Insufficient funds"

  Scenario: Transfer money between accounts
    Given a logged in customer "john@example.com"
    And the customer has a "SAVINGS" account with balance 1000
    And another customer "jane@example.com" has a "CHECKING" account with balance 500
    When the customer transfers 200 to "jane@example.com"
    Then the source account balance should be 800
    And the destination account balance should be 700

  Scenario: View transaction history
    Given a logged in customer "john@example.com"
    And the customer has a "SAVINGS" account with balance 1000
    And the customer has made 5 transactions
    When the customer views transaction history
    Then 5 transactions should be displayed