Feature: Add/UpdateBankAccount
As a user, I want to be able to add or update my bank account details so that I can receive payments for my services.

    Background:
        Given the following bank accounts exist in the system
            | account_number | account_name | bank_name | user_id |
            | 1234567890     | John Doe     | GTB       | 1       |
            | 0987654321     | Jane Doe     | Access    | 2       |
            | 1357924680     | John Doe     | Zenith    | 3       |
            | 2468135790     | Jane Doe     | UBA       | 4       |
            | 9876543210     | John Doe     | FirstBank | 5       |
            | 1234567890     | Jane Doe     | GTB       | 6       |
            | 0987654321     | John Doe     | Access    | 7       |
            | 1357924680     | Jane Doe     | Zenith    | 8       |
            | 2468135790     | John Doe     | UBA       | 9       |
            | 9876543210     | Jane Doe     | FirstBank | 10      |
    
    Scenario Outline: User can add bank account details
        Given I am logged in as a user with id "<user_id>"
        When I add a bank account with account number "<account_number>", account name "<account_name>", bank name "<bank_name>"
        Then I should see a success message "<message>"
        
        Examples:
            | user_id | account_number | account_name | bank_name | message |
            | 1       | 1234567890     | John Doe     | GTB       | Bank account added successfully |
            | 2       | 0987654321     | Jane Doe     | Access    | Bank account added successfully |
            | 3       | 1357924680     | John Doe     | Zenith    | Bank account added successfully |
            | 4       | 2468135790     | Jane Doe     | UBA       | Bank account added successfully |
            | 5       | 9876543210     | John Doe     | FirstBank | Bank account added successfully |
            | 6       | 1234567890     | Jane Doe     | GTB       | Bank account added successfully |
            | 7       | 0987654321     | John Doe     | Access    | Bank account added successfully |
            | 8       | 1357924680     | Jane Doe     | Zenith    | Bank account added successfully |
            | 9       | 2468135790     | John Doe     | UBA       | Bank account added successfully |
            | 10      | 9876543210     | Jane Doe     | FirstBank | Bank account added successfully |