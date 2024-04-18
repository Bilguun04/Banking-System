Feature: RemoveBankAccount
As a user, I want to remove a bank account from my account so that I can no longer use it to make payments.

    Background:
        Given the following bank accounts exist in the system
            | Account Number | Sort Code | Name on Account | Account Type |
            | 12345678       | 12-34-56 | John Doe         | Current      |
            | 87654321       | 65-43-21 | Jane Doe         | Savings      |
    Scenario Outline: Remove a bank account
        When the user removes the bank account with the following details
            | Account Number | Sort Code | Name on Account | Account Type |
            |12345678        | 12-34-56 | John Doe         | Current      |
        Then the bank account with the following details should be added to the system
            | Account Number | Sort Code | Name on Account | Account Type |
            | 87654321       | 65-43-21 | Jane Doe         | Savings      |