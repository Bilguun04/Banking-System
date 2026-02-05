package com.banking.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasicSteps {
    private String platformName;
    private int accountsOpened;

    @Given("the banking platform is running")
    public void theBankingPlatformIsRunning() {
        platformName = "Banking-System";
    }

    @When("a customer opens {int} accounts")
    public void aCustomerOpensAccounts(int count) {
        accountsOpened = count;
    }

    @Then("the platform should report {int} opened accounts")
    public void thePlatformShouldReportOpenedAccounts(int expected) {
        assertEquals(expected, accountsOpened);
        assertEquals("Banking-System", platformName);
    }
}
