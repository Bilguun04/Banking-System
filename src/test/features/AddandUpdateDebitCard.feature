Feature: Add/UpdateDebitCard
As an employee, I want to add or update my debit card information so that I can receive my salary on time.

    Background:
        Given the following debit cards exist in the system
            | Card Number | Expiry Date | CVV | Name on Card | Address | City | State | Zip Code |
            | 1234567890  | 12/2022     | 123 | John Doe     | 123 St  | NY   | NY    | 12345    |
            | 0987654321  | 12/2023     | 321 | Jane Doe     | 321 St  | LA   | CA    | 54321    |
     Scenario Outline: Add a new debit card
        When the user adds a new debit card with the following details
            | Card Number | Expiry Date | CVV | Name on Card | Address | City | State | Zip Code |
            | 1357924680  | 12/2024     | 456 | John Doe     | 456 St  | SF   | CA    | 65432    |
        Then the debit card with the following details should be added to the system
            | Card Number | Expiry Date | CVV | Name on Card | Address | City | State | Zip Code |
            | 1234567890  | 12/2022     | 123 | John Doe     | 123 St  | NY   | NY    | 12345    |
            | 0987654321  | 12/2023     | 321 | Jane Doe     | 321 St  | LA   | CA    | 54321    |
            | 1357924680  | 12/2024     | 456 | John Doe     | 456 St  | SF   | CA    | 65432    |