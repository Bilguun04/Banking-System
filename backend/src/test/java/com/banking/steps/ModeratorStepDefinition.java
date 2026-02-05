package com.banking.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModeratorStepDefinition {

    private String currentModeratorUsername;
    private List<AuditLog> auditLogs = new ArrayList<>();
    private List<FlaggedTransaction> flaggedTransactions = new ArrayList<>();
    private FlaggedTransaction currentFlaggedTransaction;
    private Map<String, StaffMember> staffMembers = new HashMap<>();
    private ComplianceReport complianceReport;
    private double dailyTransactionLimit = 10000;
    private boolean operationSuccess;
    private boolean incidentReportCreated;
    private boolean accountFrozen;

    @Before
    public void setUp() {
        currentModeratorUsername = null;
        auditLogs.clear();
        flaggedTransactions.clear();
        currentFlaggedTransaction = null;
        staffMembers.clear();
        complianceReport = null;
        dailyTransactionLimit = 10000;
        operationSuccess = false;
        incidentReportCreated = false;
        accountFrozen = false;
    }

    @Given("a moderator is logged in with username {string}")
    public void aModeratorIsLoggedInWithUsername(String username) {
        currentModeratorUsername = username;
        assertNotNull(currentModeratorUsername);
    }

    @Given("there are audit logs in the system")
    public void thereAreAuditLogsInTheSystem() {
        for (int i = 0; i < 10; i++) {
            AuditLog log = new AuditLog();
            log.setTimestamp(System.currentTimeMillis() - (i * 86400000L));
            log.setAction("ACTION_" + i);
            log.setPerformedBy("user" + i);
            log.setDetails("Details for action " + i);
            auditLogs.add(log);
        }
    }

    @When("the moderator requests audit logs for the last {int} days")
    public void theModeratorRequestsAuditLogsForTheLastDays(int days) {
        long cutoff = System.currentTimeMillis() - (days * 86400000L);
        auditLogs = auditLogs.stream()
                .filter(log -> log.getTimestamp() >= cutoff)
                .toList();
        operationSuccess = !auditLogs.isEmpty();
    }

    @Then("the audit logs should be displayed")
    public void theAuditLogsShouldBeDisplayed() {
        assertTrue(operationSuccess);
        assertFalse(auditLogs.isEmpty());
    }

    @And("each log should contain timestamp and action details")
    public void eachLogShouldContainTimestampAndActionDetails() {
        for (AuditLog log : auditLogs) {
            assertTrue(log.getTimestamp() > 0);
            assertNotNull(log.getAction());
            assertNotNull(log.getDetails());
        }
    }

    @Given("there are {int} flagged transactions")
    public void thereAreFlaggedTransactions(int count) {
        for (int i = 0; i < count; i++) {
            FlaggedTransaction tx = new FlaggedTransaction();
            tx.setTransactionId("TX" + i);
            tx.setAmount(1000 * (i + 1));
            tx.setCustomerEmail("customer" + i + "@example.com");
            tx.setFlagReason("Suspicious pattern detected");
            tx.setStatus("FLAGGED");
            flaggedTransactions.add(tx);
        }
    }

    @When("the moderator reviews flagged transactions")
    public void theModeratorReviewsFlaggedTransactions() {
        operationSuccess = !flaggedTransactions.isEmpty();
    }

    @Then("{int} flagged transactions should be displayed")
    public void flaggedTransactionsShouldBeDisplayed(int expected) {
        assertEquals(expected, flaggedTransactions.size());
    }

    @And("each transaction should show the flag reason")
    public void eachTransactionShouldShowTheFlagReason() {
        for (FlaggedTransaction tx : flaggedTransactions) {
            assertNotNull(tx.getFlagReason());
        }
    }

    @Given("a flagged transaction of {int} from {string}")
    public void aFlaggedTransactionOfFrom(int amount, String email) {
        FlaggedTransaction tx = new FlaggedTransaction();
        tx.setTransactionId("TX_" + System.currentTimeMillis());
        tx.setAmount(amount);
        tx.setCustomerEmail(email);
        tx.setFlagReason("Large transaction amount");
        tx.setStatus("FLAGGED");
        flaggedTransactions.add(tx);
        currentFlaggedTransaction = tx;
    }

    @When("the moderator marks the transaction as legitimate")
    public void theModeratorMarksTheTransactionAsLegitimate() {
        if (currentFlaggedTransaction != null) {
            currentFlaggedTransaction.setStatus("CLEARED");
            currentFlaggedTransaction.setReviewedBy(currentModeratorUsername);
            operationSuccess = true;
        }
    }

    @Then("the transaction flag should be removed")
    public void theTransactionFlagShouldBeRemoved() {
        assertEquals("CLEARED", currentFlaggedTransaction.getStatus());
    }

    @And("the transaction should proceed normally")
    public void theTransactionShouldProceedNormally() {
        assertTrue(operationSuccess);
    }

    @When("the moderator blocks the transaction as fraudulent")
    public void theModeratorBlocksTheTransactionAsFraudulent() {
        if (currentFlaggedTransaction != null) {
            currentFlaggedTransaction.setStatus("BLOCKED");
            currentFlaggedTransaction.setReviewedBy(currentModeratorUsername);
            accountFrozen = true;
            incidentReportCreated = true;
            operationSuccess = true;
        }
    }

    @Then("the transaction should be cancelled")
    public void theTransactionShouldBeCancelled() {
        assertEquals("BLOCKED", currentFlaggedTransaction.getStatus());
    }

    @And("the customer account should be frozen")
    public void theCustomerAccountShouldBeFrozen() {
        assertTrue(accountFrozen);
    }

    @And("an incident report should be created")
    public void anIncidentReportShouldBeCreated() {
        assertTrue(incidentReportCreated);
    }

    @Given("a staff member {string} exists")
    public void aStaffMemberExists(String username) {
        StaffMember staff = new StaffMember();
        staff.setUsername(username);
        staff.setPermissions(new HashSet<>());
        staffMembers.put(username, staff);
    }

    @When("the moderator grants {string} permission to {string}")
    public void theModeratorGrantsPermissionTo(String permission, String username) {
        StaffMember staff = staffMembers.get(username);
        if (staff != null) {
            staff.getPermissions().add(permission);
            operationSuccess = true;
        }
    }

    @Then("{string} should have {string} permission")
    public void shouldHavePermission(String username, String permission) {
        StaffMember staff = staffMembers.get(username);
        assertTrue(staff.getPermissions().contains(permission));
    }

    @Given("a staff member {string} has {string} permission")
    public void aStaffMemberHasPermission(String username, String permission) {
        StaffMember staff = new StaffMember();
        staff.setUsername(username);
        staff.setPermissions(new HashSet<>());
        staff.getPermissions().add(permission);
        staffMembers.put(username, staff);
    }

    @When("the moderator revokes {string} permission from {string}")
    public void theModeratorRevokesPermissionFrom(String permission, String username) {
        StaffMember staff = staffMembers.get(username);
        if (staff != null) {
            staff.getPermissions().remove(permission);
            operationSuccess = true;
        }
    }

    @Then("{string} should not have {string} permission")
    public void shouldNotHavePermission(String username, String permission) {
        StaffMember staff = staffMembers.get(username);
        assertFalse(staff.getPermissions().contains(permission));
    }

    @Given("transactions exist for the current month")
    public void transactionsExistForTheCurrentMonth() {
        // Simulating transaction data exists
        operationSuccess = true;
    }

    @When("the moderator generates a compliance report")
    public void theModeratorGeneratesAComplianceReport() {
        complianceReport = new ComplianceReport();
        complianceReport.setTotalTransactionCount(1500);
        complianceReport.setFlaggedTransactionCount(25);
        complianceReport.setFlaggedPercentage(1.67);
        complianceReport.setAverageTransactionAmount(2500.00);
        complianceReport.setGeneratedBy(currentModeratorUsername);
        operationSuccess = true;
    }

    @Then("the report should include total transaction count")
    public void theReportShouldIncludeTotalTransactionCount() {
        assertNotNull(complianceReport);
        assertTrue(complianceReport.getTotalTransactionCount() > 0);
    }

    @And("the report should include flagged transaction percentage")
    public void theReportShouldIncludeFlaggedTransactionPercentage() {
        assertTrue(complianceReport.getFlaggedPercentage() >= 0);
    }

    @And("the report should include average transaction amount")
    public void theReportShouldIncludeAverageTransactionAmount() {
        assertTrue(complianceReport.getAverageTransactionAmount() > 0);
    }

    @Given("the current daily transaction limit is {int}")
    public void theCurrentDailyTransactionLimitIs(int limit) {
        dailyTransactionLimit = limit;
    }

    @When("the moderator sets the daily transaction limit to {int}")
    public void theModeratorSetsTheDailyTransactionLimitTo(int newLimit) {
        dailyTransactionLimit = newLimit;
        operationSuccess = true;
    }

    @Then("the new daily transaction limit should be {int}")
    public void theNewDailyTransactionLimitShouldBe(int expected) {
        assertEquals(expected, dailyTransactionLimit, 0.01);
    }

    @And("all customers should be subject to the new limit")
    public void allCustomersShouldBeSubjectToTheNewLimit() {
        assertTrue(operationSuccess);
    }

    // Inner classes
    static class AuditLog {
        private long timestamp;
        private String action;
        private String performedBy;
        private String details;

        public long getTimestamp() { return timestamp; }
        public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
        public String getPerformedBy() { return performedBy; }
        public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
    }

    static class FlaggedTransaction {
        private String transactionId;
        private double amount;
        private String customerEmail;
        private String flagReason;
        private String status;
        private String reviewedBy;

        public String getTransactionId() { return transactionId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getCustomerEmail() { return customerEmail; }
        public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
        public String getFlagReason() { return flagReason; }
        public void setFlagReason(String flagReason) { this.flagReason = flagReason; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getReviewedBy() { return reviewedBy; }
        public void setReviewedBy(String reviewedBy) { this.reviewedBy = reviewedBy; }
    }

    static class StaffMember {
        private String username;
        private Set<String> permissions;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public Set<String> getPermissions() { return permissions; }
        public void setPermissions(Set<String> permissions) { this.permissions = permissions; }
    }

    static class ComplianceReport {
        private int totalTransactionCount;
        private int flaggedTransactionCount;
        private double flaggedPercentage;
        private double averageTransactionAmount;
        private String generatedBy;

        public int getTotalTransactionCount() { return totalTransactionCount; }
        public void setTotalTransactionCount(int totalTransactionCount) { this.totalTransactionCount = totalTransactionCount; }
        public int getFlaggedTransactionCount() { return flaggedTransactionCount; }
        public void setFlaggedTransactionCount(int flaggedTransactionCount) { this.flaggedTransactionCount = flaggedTransactionCount; }
        public double getFlaggedPercentage() { return flaggedPercentage; }
        public void setFlaggedPercentage(double flaggedPercentage) { this.flaggedPercentage = flaggedPercentage; }
        public double getAverageTransactionAmount() { return averageTransactionAmount; }
        public void setAverageTransactionAmount(double averageTransactionAmount) { this.averageTransactionAmount = averageTransactionAmount; }
        public String getGeneratedBy() { return generatedBy; }
        public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }
    }
}