package com.banking.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerStepDefinition {

    private Map<String, Customer> customers = new HashMap<>();
    private Map<String, Account> accounts = new HashMap<>();
    private Customer currentCustomer;
    private Account currentAccount;
    private String accessToken;
    private boolean operationSuccess;
    private String errorMessage;
    private List<Transaction> transactionHistory = new ArrayList<>();
    private boolean notificationSent;

    @Before
    public void setUp() {
        customers.clear();
        accounts.clear();
        currentCustomer = null;
        currentAccount = null;
        accessToken = null;
        operationSuccess = false;
        errorMessage = null;
        transactionHistory.clear();
        notificationSent = false;
    }

    @Given("the banking system is initialized")
    public void theBankingSystemIsInitialized() {
        // Initialize any required system state
        operationSuccess = true;
    }

    @Given("a new customer with name {string} and email {string}")
    public void aNewCustomerWithNameAndEmail(String name, String email) {
        currentCustomer = new Customer();
        currentCustomer.setName(name);
        currentCustomer.setEmail(email);
    }

    @When("the customer registers with password {string}")
    public void theCustomerRegistersWithPassword(String password) {
        if (currentCustomer != null && password.length() >= 8) {
            currentCustomer.setPassword(password);
            currentCustomer.setRegistered(true);
            customers.put(currentCustomer.getEmail(), currentCustomer);
            operationSuccess = true;
            notificationSent = true;
        } else {
            operationSuccess = false;
            errorMessage = "Registration failed";
        }
    }

    @Then("the registration should be successful")
    public void theRegistrationShouldBeSuccessful() {
        assertTrue(operationSuccess, "Registration should be successful");
    }

    @And("the customer should receive a welcome notification")
    public void theCustomerShouldReceiveAWelcomeNotification() {
        assertTrue(notificationSent, "Welcome notification should be sent");
    }

    @Given("a registered customer with email {string} and password {string}")
    public void aRegisteredCustomerWithEmailAndPassword(String email, String password) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setName("Test User");
        customer.setRegistered(true);
        customers.put(email, customer);
    }

    @When("the customer attempts to login")
    public void theCustomerAttemptsToLogin() {
        String email = customers.keySet().iterator().next();
        Customer customer = customers.get(email);
        if (customer != null && customer.isRegistered()) {
            currentCustomer = customer;
            accessToken = "jwt_token_" + System.currentTimeMillis();
            operationSuccess = true;
        } else {
            operationSuccess = false;
            errorMessage = "Invalid credentials";
        }
    }

    @When("the customer attempts to login with password {string}")
    public void theCustomerAttemptsToLoginWithPassword(String password) {
        String email = customers.keySet().iterator().next();
        Customer customer = customers.get(email);
        if (customer != null && customer.getPassword().equals(password)) {
            currentCustomer = customer;
            accessToken = "jwt_token_" + System.currentTimeMillis();
            operationSuccess = true;
        } else {
            operationSuccess = false;
            errorMessage = "Invalid credentials";
        }
    }

    @Then("the login should be successful")
    public void theLoginShouldBeSuccessful() {
        assertTrue(operationSuccess, "Login should be successful");
    }

    @Then("the login should fail")
    public void theLoginShouldFail() {
        assertFalse(operationSuccess, "Login should fail");
    }

    @And("the customer should receive an access token")
    public void theCustomerShouldReceiveAnAccessToken() {
        assertNotNull(accessToken, "Access token should not be null");
    }

    @And("the error message should be {string}")
    public void theErrorMessageShouldBe(String expected) {
        assertEquals(expected, errorMessage);
    }

    @Given("a logged in customer {string}")
    public void aLoggedInCustomer(String email) {
        Customer customer = customers.getOrDefault(email, new Customer());
        customer.setEmail(email);
        customer.setRegistered(true);
        customers.put(email, customer);
        currentCustomer = customer;
        accessToken = "jwt_token_" + System.currentTimeMillis();
    }

    @When("the customer opens a {string} account")
    public void theCustomerOpensAAccount(String accountType) {
        Account account = new Account();
        account.setAccountType(accountType);
        account.setBalance(0.0);
        account.setOwnerEmail(currentCustomer.getEmail());
        account.setAccountNumber("ACC" + System.currentTimeMillis());
        accounts.put(account.getAccountNumber(), account);
        currentAccount = account;
        operationSuccess = true;
    }

    @Then("a new account should be created")
    public void aNewAccountShouldBeCreated() {
        assertNotNull(currentAccount, "Account should be created");
        assertTrue(operationSuccess, "Account creation should be successful");
    }

    @Then("the account balance should be {int}")
    public void theAccountBalanceShouldBe(int expected) {
        assertEquals(expected, currentAccount.getBalance(), 0.01);
    }

    @Given("the customer has a {string} account with balance {int}")
    public void theCustomerHasAAccountWithBalance(String accountType, int balance) {
        Account account = new Account();
        account.setAccountType(accountType);
        account.setBalance(balance);
        account.setOwnerEmail(currentCustomer.getEmail());
        account.setAccountNumber("ACC" + System.currentTimeMillis());
        accounts.put(account.getAccountNumber(), account);
        currentAccount = account;
    }

    @When("the customer deposits {int}")
    public void theCustomerDeposits(int amount) {
        currentAccount.setBalance(currentAccount.getBalance() + amount);
        Transaction tx = new Transaction("DEPOSIT", amount, currentAccount.getAccountNumber());
        transactionHistory.add(tx);
        operationSuccess = true;
    }

    @When("the customer withdraws {int}")
    public void theCustomerWithdraws(int amount) {
        if (currentAccount.getBalance() >= amount) {
            currentAccount.setBalance(currentAccount.getBalance() - amount);
            Transaction tx = new Transaction("WITHDRAWAL", amount, currentAccount.getAccountNumber());
            transactionHistory.add(tx);
            operationSuccess = true;
        } else {
            operationSuccess = false;
            errorMessage = "Insufficient funds";
        }
    }

    @Then("the withdrawal should fail")
    public void theWithdrawalShouldFail() {
        assertFalse(operationSuccess, "Withdrawal should fail");
    }

    @And("a transaction record should be created")
    public void aTransactionRecordShouldBeCreated() {
        assertFalse(transactionHistory.isEmpty(), "Transaction history should not be empty");
    }

    @Given("another customer {string} has a {string} account with balance {int}")
    public void anotherCustomerHasAAccountWithBalance(String email, String accountType, int balance) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setRegistered(true);
        customers.put(email, customer);
        
        Account account = new Account();
        account.setAccountType(accountType);
        account.setBalance(balance);
        account.setOwnerEmail(email);
        account.setAccountNumber("ACC_" + email);
        accounts.put(account.getAccountNumber(), account);
    }

    @When("the customer transfers {int} to {string}")
    public void theCustomerTransfersTo(int amount, String targetEmail) {
        Account targetAccount = accounts.get("ACC_" + targetEmail);
        if (currentAccount.getBalance() >= amount && targetAccount != null) {
            currentAccount.setBalance(currentAccount.getBalance() - amount);
            targetAccount.setBalance(targetAccount.getBalance() + amount);
            operationSuccess = true;
        } else {
            operationSuccess = false;
            errorMessage = "Transfer failed";
        }
    }

    @Then("the source account balance should be {int}")
    public void theSourceAccountBalanceShouldBe(int expected) {
        assertEquals(expected, currentAccount.getBalance(), 0.01);
    }

    @Then("the destination account balance should be {int}")
    public void theDestinationAccountBalanceShouldBe(int expected) {
        Account targetAccount = accounts.values().stream()
                .filter(a -> !a.getAccountNumber().equals(currentAccount.getAccountNumber()))
                .findFirst().orElse(null);
        assertNotNull(targetAccount);
        assertEquals(expected, targetAccount.getBalance(), 0.01);
    }

    @Given("the customer has made {int} transactions")
    public void theCustomerHasMadeTransactions(int count) {
        for (int i = 0; i < count; i++) {
            transactionHistory.add(new Transaction("TEST", 100 * (i + 1), currentAccount.getAccountNumber()));
        }
    }

    @When("the customer views transaction history")
    public void theCustomerViewsTransactionHistory() {
        operationSuccess = !transactionHistory.isEmpty();
    }

    @Then("{int} transactions should be displayed")
    public void transactionsShouldBeDisplayed(int expected) {
        assertEquals(expected, transactionHistory.size());
    }

    // Inner classes for testing
    static class Customer {
        private String name;
        private String email;
        private String password;
        private boolean registered;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public boolean isRegistered() { return registered; }
        public void setRegistered(boolean registered) { this.registered = registered; }
    }

    static class Account {
        private String accountNumber;
        private String accountType;
        private double balance;
        private String ownerEmail;
        private String status = "ACTIVE";

        public String getAccountNumber() { return accountNumber; }
        public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
        public String getAccountType() { return accountType; }
        public void setAccountType(String accountType) { this.accountType = accountType; }
        public double getBalance() { return balance; }
        public void setBalance(double balance) { this.balance = balance; }
        public String getOwnerEmail() { return ownerEmail; }
        public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    static class Transaction {
        private String type;
        private double amount;
        private String accountNumber;
        private long timestamp;

        public Transaction(String type, double amount, String accountNumber) {
            this.type = type;
            this.amount = amount;
            this.accountNumber = accountNumber;
            this.timestamp = System.currentTimeMillis();
        }

        public String getType() { return type; }
        public double getAmount() { return amount; }
        public String getAccountNumber() { return accountNumber; }
        public long getTimestamp() { return timestamp; }
    }
}