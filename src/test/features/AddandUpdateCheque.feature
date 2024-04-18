Feature: Add/UpdateCheque
As a user, I want to add or update cheque details so that I can make payments using cheques

    Background:
        Given the following cheques exist in the system
            | cheque_number | cheque_date | cheque_amount | cheque_status | user_id |
            | 1234567890    | 2021-01-01  | 1000          | Pending       | 1       |
            | 0987654321    | 2021-02-01  | 2000          | Pending       | 2       |
            | 1357924680    | 2021-03-01  | 3000          | Pending       | 3       |
            | 2468135790    | 2021-04-01  | 4000          | Pending       | 4       |
            | 9876543210    | 2021-05-01  | 5000          | Pending       | 5       |
            | 1234567890    | 2021-06-01  | 6000          | Pending       | 6       |
            | 0987654321    | 2021-07-01  | 7000          | Pending       | 7       |
            | 1357924680    | 2021-08-01  | 8000          | Pending       | 8       |
            | 2468135790    | 2021-09-01  | 9000          | Pending       | 9       |
            | 9876543210    | 2021-10-01  | 10000         | Pending       | 10      |
    
    Scenario Outline: Add cheque details
        When the user adds a new cheque with the following details
            | cheque_number | cheque_date | cheque_amount | cheque_status | user_id |
            | 1918273648    | 2021-11-01  | 11000         | Pending       | 11      |
        Then the cheque with the following details should be added to the system
            | cheque_number | cheque_date | cheque_amount | cheque_status | user_id |
            | 1234567890    | 2021-01-01  | 1000          | Pending       | 1       |
            | 0987654321    | 2021-02-01  | 2000          | Pending       | 2       |
            | 1357924680    | 2021-03-01  | 3000          | Pending       | 3       |
            | 2468135790    | 2021-04-01  | 4000          | Pending       | 4       |
            | 9876543210    | 2021-05-01  | 5000          | Pending       | 5       |
            | 1234567890    | 2021-06-01  | 6000          | Pending       | 6       |
            | 0987654321    | 2021-07-01  | 7000          | Pending       | 7       |
            | 1357924680    | 2021-08-01  | 8000          | Pending       | 8       |
            | 2468135790    | 2021-09-01  | 9000          | Pending       | 9       |
            | 9876543210    | 2021-10-01  | 10000         | Pending       | 10      |
            | 1918273648    | 2021-11-01  | 11000         | Pending       | 11      |