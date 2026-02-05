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

public class StaffStepDefinition {

    private String currentStaffUsername;
    private Map<String, CustomerInfo> customers = new HashMap<>();
    private List<AccountRequest> pendingAccountRequests = new ArrayList<>();
    private List<LoanApplication> pendingLoanApplications = new ArrayList<>();
    private CustomerInfo searchedCustomer;
    private AccountRequest currentRequest;
    private LoanApplication currentLoanApplication;
    private boolean operationSuccess;
    private boolean customerNotified;
    private String notificationReason;
    private int totalPendingItems;

    @Before
    public void setUp() {
        currentStaffUsername = null;
        customers.clear();
        pendingAccountRequests.clear();
        pendingLoanApplications.clear();
        searchedCustomer = null;
        currentRequest = null;
        currentLoanApplication = null;
        operationSuccess = false;
        customerNotified = false;
        notificationReason = null;
        totalPendingItems = 0;
    }

    @Given("a staff member is logged in with username {string}")
    public void aStaffMemberIsLoggedInWithUsername(String username) {
        currentStaffUsername = username;
        assertNotNull(currentStaffUsername, "Staff should be logged in");
    }

    @Given("a customer exists with email {string}")
    public void aCustomerExistsWithEmail(String email) {
        CustomerInfo customer = new CustomerInfo();
        customer.setEmail(email);
        customer.setName("John Doe");
        customer.setPhone("555-1234");
        customers.put(email, customer);
    }

    @When("the staff searches for customer {string}")
    public void theStaffSearchesForCustomer(String email) {
        searchedCustomer = customers.get(email);
        operationSuccess = searchedCustomer != null;
    }

    @Then("the customer details should be displayed")
    public void theCustomerDetailsShouldBeDisplayed() {
        assertNotNull(searchedCustomer, "Customer details should be displayed");
    }

    @And("the customer name should be {string}")
    public void theCustomerNameShouldBe(String expectedName) {
        assertEquals(expectedName, searchedCustomer.getName());
    }

    @Given("a pending account opening request from {string}")
    public void aPendingAccountOpeningRequestFrom(String email) {
        AccountRequest request = new AccountRequest();
        request.setCustomerEmail(email);
        request.setStatus("PENDING");
        request.setAccountType("SAVINGS");
        pendingAccountRequests.add(request);
        currentRequest = request;
        
        CustomerInfo customer = new CustomerInfo();
        customer.setEmail(email);
        customers.put(email, customer);
    }

    @When("the staff approves the request")
    public void theStaffApprovesTheRequest() {
        if (currentRequest != null) {
            currentRequest.setStatus("APPROVED");
            currentRequest.setApprovedBy(currentStaffUsername);
            operationSuccess = true;
            customerNotified = true;
        }
    }

    @Then("the account should be activated")
    public void theAccountShouldBeActivated() {
        assertEquals("APPROVED", currentRequest.getStatus());
    }

    @And("the customer should be notified")
    public void theCustomerShouldBeNotified() {
        assertTrue(customerNotified, "Customer should be notified");
    }

    @When("the staff rejects the request with reason {string}")
    public void theStaffRejectsTheRequestWithReason(String reason) {
        if (currentRequest != null) {
            currentRequest.setStatus("REJECTED");
            currentRequest.setRejectionReason(reason);
            operationSuccess = true;
            customerNotified = true;
            notificationReason = reason;
        }
    }

    @Then("the request should be marked as rejected")
    public void theRequestShouldBeMarkedAsRejected() {
        assertEquals("REJECTED", currentRequest.getStatus());
    }

    @And("the customer should be notified with the reason")
    public void theCustomerShouldBeNotifiedWithTheReason() {
        assertTrue(customerNotified);
        assertNotNull(notificationReason);
    }

    @Given("a customer {string} has an active account")
    public void aCustomerHasAnActiveAccount(String email) {
        CustomerInfo customer = new CustomerInfo();
        customer.setEmail(email);
        customer.setAccountStatus("ACTIVE");
        customers.put(email, customer);
    }

    @When("the staff freezes the account with reason {string}")
    public void theStaffFreezesTheAccountWithReason(String reason) {
        String email = customers.keySet().iterator().next();
        CustomerInfo customer = customers.get(email);
        if (customer != null) {
            customer.setAccountStatus("FROZEN");
            customer.setFreezeReason(reason);
            operationSuccess = true;
            customerNotified = true;
        }
    }

    @Then("the account status should be {string}")
    public void theAccountStatusShouldBe(String expectedStatus) {
        CustomerInfo customer = customers.values().iterator().next();
        assertEquals(expectedStatus, customer.getAccountStatus());
    }

    @Given("a customer {string} has a frozen account")
    public void aCustomerHasAFrozenAccount(String email) {
        CustomerInfo customer = new CustomerInfo();
        customer.setEmail(email);
        customer.setAccountStatus("FROZEN");
        customers.put(email, customer);
    }

    @When("the staff unfreezes the account")
    public void theStaffUnfreezesTheAccount() {
        CustomerInfo customer = customers.values().iterator().next();
        if (customer != null) {
            customer.setAccountStatus("ACTIVE");
            operationSuccess = true;
            customerNotified = true;
        }
    }

    @Given("a customer {string} has a loan application for {int}")
    public void aCustomerHasALoanApplicationFor(String email, int amount) {
        LoanApplication loan = new LoanApplication();
        loan.setCustomerEmail(email);
        loan.setAmount(amount);
        loan.setStatus("PENDING");
        pendingLoanApplications.add(loan);
        currentLoanApplication = loan;
        
        CustomerInfo customer = new CustomerInfo();
        customer.setEmail(email);
        customer.setAccountBalance(0);
        customers.put(email, customer);
    }

    @When("the staff reviews the application")
    public void theStaffReviewsTheApplication() {
        assertNotNull(currentLoanApplication, "Loan application should exist");
    }

    @And("the staff approves the loan with interest rate {double}")
    public void theStaffApprovesTheLoanWithInterestRate(double rate) {
        if (currentLoanApplication != null) {
            currentLoanApplication.setStatus("APPROVED");
            currentLoanApplication.setInterestRate(rate);
            currentLoanApplication.setApprovedBy(currentStaffUsername);
            operationSuccess = true;
        }
    }

    @Then("the loan should be disbursed")
    public void theLoanShouldBeDisbursed() {
        assertEquals("APPROVED", currentLoanApplication.getStatus());
        assertTrue(operationSuccess);
    }

    @And("the customer account should be credited with {int}")
    public void theCustomerAccountShouldBeCreditedWith(int amount) {
        CustomerInfo customer = customers.get(currentLoanApplication.getCustomerEmail());
        customer.setAccountBalance(customer.getAccountBalance() + amount);
        assertEquals(amount, customer.getAccountBalance(), 0.01);
    }

    @Given("there are {int} pending account requests")
    public void thereArePendingAccountRequests(int count) {
        for (int i = 0; i < count; i++) {
            AccountRequest request = new AccountRequest();
            request.setCustomerEmail("customer" + i + "@example.com");
            request.setStatus("PENDING");
            pendingAccountRequests.add(request);
        }
    }

    @And("there are {int} pending loan applications")
    public void thereArePendingLoanApplications(int count) {
        for (int i = 0; i < count; i++) {
            LoanApplication loan = new LoanApplication();
            loan.setCustomerEmail("customer" + i + "@example.com");
            loan.setAmount(1000 * (i + 1));
            loan.setStatus("PENDING");
            pendingLoanApplications.add(loan);
        }
    }

    @When("the staff views pending requests")
    public void theStaffViewsPendingRequests() {
        totalPendingItems = pendingAccountRequests.size() + pendingLoanApplications.size();
    }

    @Then("{int} total pending items should be displayed")
    public void totalPendingItemsShouldBeDisplayed(int expected) {
        assertEquals(expected, totalPendingItems);
    }

    // Inner classes
    static class CustomerInfo {
        private String email;
        private String name;
        private String phone;
        private String accountStatus = "ACTIVE";
        private String freezeReason;
        private double accountBalance;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getAccountStatus() { return accountStatus; }
        public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }
        public String getFreezeReason() { return freezeReason; }
        public void setFreezeReason(String freezeReason) { this.freezeReason = freezeReason; }
        public double getAccountBalance() { return accountBalance; }
        public void setAccountBalance(double accountBalance) { this.accountBalance = accountBalance; }
    }

    static class AccountRequest {
        private String customerEmail;
        private String status;
        private String accountType;
        private String approvedBy;
        private String rejectionReason;

        public String getCustomerEmail() { return customerEmail; }
        public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getAccountType() { return accountType; }
        public void setAccountType(String accountType) { this.accountType = accountType; }
        public String getApprovedBy() { return approvedBy; }
        public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
        public String getRejectionReason() { return rejectionReason; }
        public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    }

    static class LoanApplication {
        private String customerEmail;
        private double amount;
        private String status;
        private double interestRate;
        private String approvedBy;

        public String getCustomerEmail() { return customerEmail; }
        public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public double getInterestRate() { return interestRate; }
        public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
        public String getApprovedBy() { return approvedBy; }
        public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    }
}