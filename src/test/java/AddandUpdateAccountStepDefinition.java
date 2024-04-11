package test.java;

import io.cucumber.api.java.en.Given;
import io.cucumber.api.java.en.When;
import io.cucumber.api.java.en.Then;
import main.java.bankingsystem.core.controller.*;
import main.java.bankingsystem.core.model.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        Account addedAccount = accountController.getAccount(account.getAccountNumber());
        assertNotNull(addedAccount);
        assertEquals(account.getAccountNumber(), addedAccount.getAccountNumber());
    }
}