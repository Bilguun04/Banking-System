Feature: Moderator Operations
  As a bank moderator
  I want to oversee banking operations
  So that I can ensure compliance and security

  Background:
    Given the banking system is initialized
    And a moderator is logged in with username "mod1"

  Scenario: View system audit logs
    Given there are audit logs in the system
    When the moderator requests audit logs for the last 7 days
    Then the audit logs should be displayed
    And each log should contain timestamp and action details

  Scenario: Review flagged transactions
    Given there are 5 flagged transactions
    When the moderator reviews flagged transactions
    Then 5 flagged transactions should be displayed
    And each transaction should show the flag reason

  Scenario: Approve flagged transaction
    Given a flagged transaction of 50000 from "john@example.com"
    When the moderator marks the transaction as legitimate
    Then the transaction flag should be removed
    And the transaction should proceed normally

  Scenario: Block fraudulent transaction
    Given a flagged transaction of 50000 from "john@example.com"
    When the moderator blocks the transaction as fraudulent
    Then the transaction should be cancelled
    And the customer account should be frozen
    And an incident report should be created

  Scenario: Manage staff permissions
    Given a staff member "staff1" exists
    When the moderator grants "LOAN_APPROVAL" permission to "staff1"
    Then "staff1" should have "LOAN_APPROVAL" permission

  Scenario: Revoke staff permissions
    Given a staff member "staff1" has "LOAN_APPROVAL" permission
    When the moderator revokes "LOAN_APPROVAL" permission from "staff1"
    Then "staff1" should not have "LOAN_APPROVAL" permission

  Scenario: Generate compliance report
    Given transactions exist for the current month
    When the moderator generates a compliance report
    Then the report should include total transaction count
    And the report should include flagged transaction percentage
    And the report should include average transaction amount

  Scenario: Set transaction limits
    Given the current daily transaction limit is 10000
    When the moderator sets the daily transaction limit to 15000
    Then the new daily transaction limit should be 15000
    And all customers should be subject to the new limit