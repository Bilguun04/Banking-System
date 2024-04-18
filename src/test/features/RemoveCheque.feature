Feature: RemoveCheque
As a user, I want to remove a cheque so that I can delete a cheque from the system.

    Background:
        Given the following cheques exist in the system
            |cheque_number |amount  |date        | status |
            | 1234         | 100    | 2020-01-01 | cleared |
            | 5678         | 200    | 2020-01-02 | cleared |
            | 9101         | 300    | 2020-01-03 | cleared |
    
    Scenario: Remove a cheque
        When the user removes the cheque with the following details
            |cheque_number |amount  |date        | status |
            | 1234         | 100    | 2020-01-01 | cleared |
        Then the cheque with the following details should be removed from the system
            |cheque_number |amount  |date        | status  |
            | 5678         | 200    | 2020-01-02 | cleared |
            | 9101         | 300    | 2020-01-03 | cleared |