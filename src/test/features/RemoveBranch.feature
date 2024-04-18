Feature: RemoveBranch
As an employee, I want to remove a branch from the repository so that I can keep the repository clean and organized.

    Background:
        Given the following branches exist in the system
            | branchName |
            | branch1    |
            | branch2    |
            | branch3    |
            | branch4    |
            | branch5    |
            | branch6    |
            | branch7    |
            | branch8    |
            | branch9    |
            | branch10   |
        
    Scenario: Remove a branch
        When the user removes the branch with the following details
            | branchName |
            | branch10   |
        Then the branch with the following details should be removed from the system
            | branchName |
            | branch1    |
            | branch2    |
            | branch3    |
            | branch4    |
            | branch5    |
            | branch6    |
            | branch7    |
            | branch8    |
            | branch9    |