package test.java;

import io.cucumber.junit.Cucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import main.java.bankingsystem.core.controller.*;
import main.java.bankingsystem.core.model.*;

public class AddandUpdateAccountStepDefinition {

    private AccountController accountController;
    private Account account;

    @Given("I have an account controller")
    public void i_have_an_account_controller() {
        accountController = new AccountController();
    }

    @When("I add an account with account number {string}")
    public void i_add_an_account_with_account_number(String accountNumber) {
        account = new Account();
        account.setAccountNumber(accountNumber);
        accountController.addAccount(account);
    }

    @Then("The account should be added")
    public void the_account_should_be_added() {
        // Code to check that the account was added
    }

    @When("I update the account with account number {string}")
    public void i_update_the_account_with_account_number(String accountNumber) {
        account.setAccountNumber(accountNumber);
        accountController.updateAccount(account);
    }

    @Then("The account should be updated")
    public void the_account_should_be_updated() {
        // Code to check that the account was updated
    }
}