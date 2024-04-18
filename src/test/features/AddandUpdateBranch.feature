Feature: Add/UpdateBranch
As an employee, I want to add a new branch to the system or update an existing branch.

    Background: 
        Given the following branches exist in the system
        | Branch Name | Branch Code | Address | Phone Number |
        | Branch 1    | 001         | Address 1 | 1234567890 |
        | Branch 2    | 002         | Address 2 | 1234567890 |
        | Branch 3    | 003         | Address 3 | 1234567890 |
        | Branch 4    | 004         | Address 4 | 1234567890 |
    
    Scenario: Add a new branch
        When the user adds a new branch with the following details
        | Branch Name | Branch Code | Address | Phone Number |
        | Branch 5    | 005         | Address 5 | 1234567890 |
        Then the branch with the following details should be added to the system
        | Branch Name | Branch Code | Address | Phone Number |
        | Branch 1    | 001         | Address 1 | 1234567890 |
        | Branch 2    | 002         | Address 2 | 1234567890 |
        | Branch 3    | 003         | Address 3 | 1234567890 |
        | Branch 4    | 004         | Address 4 | 1234567890 |
        | Branch 5    | 005         | Address 5 | 1234567890 |