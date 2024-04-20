package src.test.main;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import src.main.controller.BankAccountController;
import src.main.core.BankAccount;

public class AddandUpdateBankAccountStepDefinition {
    @Given("the following bank accounts exist in the system")
    public void the_following_bank_accounts_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user adds a new bank account with the following details")
    public void the_user_adds_a_new_bank_account_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the bank account with the following details should be added to the system")
    public void the_bank_account_with_the_following_details_should_be_added_to_the_system(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    public static void main(String[] args) {
        BankAccountController bankAccountController = new BankAccountController();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("1234567890");
        bankAccount.setAccountHolderName("John Doe");
        bankAccount.setBankName("Bank of America");
        bankAccount.setBranchName("New York");
        bankAccount.setIfscCode("BOFAUS3N");
        bankAccount.setAccountType("Savings");
        bankAccount.setBalance(1000.0);
        bankAccountController.addBankAccount(bankAccount);
        System.out.println("Bank Account added successfully");
    }
}
