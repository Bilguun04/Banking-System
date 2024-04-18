Feature: RemoveDebitCard
As a user, I want to remove a debit card from my account so that I can no longer use it to make purchases.

Background:
    Given the following debit cards exist in the system
        | Card Number | Expiry Date | CVV | Name on Card | Billing Address |
        | 1234567890  | 12/20       | 123 | John Doe     | 123 Main St     |
        | 0987654321  | 12/21       | 321 | Jane Doe     | 456 Elm St      |
        | 1357924680  | 12/22       | 456 | Jim Doe      | 789 Oak St      |
    
Scenario: User removes a debit card from their account
    When the user removes the bank account with the following details
        | Card Number | Expiry Date | CVV | Name on Card | Billing Address |
        | 1234567890  | 12/20       | 123 | John Doe     | 123 Main St     |
    Then the debit card with the following details should be removed from the system
        | Card Number | Expiry Date | CVV | Name on Card | Billing Address |
        | 0987654321  | 12/21       | 321 | Jane Doe     | 456 Elm St      |
        | 1357924680  | 12/22       | 456 | Jim Doe      | 789 Oak St      |