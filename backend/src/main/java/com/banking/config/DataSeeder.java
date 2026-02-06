package com.banking.config;

import com.banking.entity.BankAccount;
import com.banking.entity.Branch;
import com.banking.repository.BankAccountRepository;
import com.banking.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("!test") // Don't run during tests
public class DataSeeder implements CommandLineRunner {

    private final BranchRepository branchRepository;
    private final BankAccountRepository bankAccountRepository;

    @Override
    public void run(String... args) {
        if (branchRepository.count() == 0) {
            seedBranches();
            seedBankAccounts();
            log.info("Database seeding completed successfully!");
        } else {
            log.info("Database already contains data, skipping seeding.");
        }
    }

    private void seedBranches() {
        List<Branch> branches = Arrays.asList(
            createBranch("Main Branch", "123 Main Street, Downtown", "+1-555-0100", "MB001"),
            createBranch("North Branch", "456 North Avenue, Uptown", "+1-555-0101", "NB001"),
            createBranch("South Branch", "789 South Boulevard, Midtown", "+1-555-0102", "SB001"),
            createBranch("East Branch", "321 East Road, Eastside", "+1-555-0103", "EB001"),
            createBranch("West Branch", "654 West Lane, Westside", "+1-555-0104", "WB001")
        );
        
        branchRepository.saveAll(branches);
        log.info("Seeded {} branches", branches.size());
    }

    private void seedBankAccounts() {
        Branch mainBranch = branchRepository.findByCode("MB001");
        Branch northBranch = branchRepository.findByCode("NB001");
        Branch southBranch = branchRepository.findByCode("SB001");
        Branch eastBranch = branchRepository.findByCode("EB001");
        Branch westBranch = branchRepository.findByCode("WB001");

        List<BankAccount> accounts = Arrays.asList(
            // Main Branch accounts
            createAccount("1001000001", "John Smith", new BigDecimal("5000.00"), "SAVINGS", "USD", mainBranch),
            createAccount("1001000002", "Jane Doe", new BigDecimal("12500.50"), "CHECKING", "USD", mainBranch),
            createAccount("1001000003", "Robert Johnson", new BigDecimal("75000.00"), "SAVINGS", "USD", mainBranch),
            
            // North Branch accounts
            createAccount("1002000001", "Emily Davis", new BigDecimal("3200.75"), "CHECKING", "USD", northBranch),
            createAccount("1002000002", "Michael Wilson", new BigDecimal("18900.00"), "SAVINGS", "USD", northBranch),
            
            // South Branch accounts
            createAccount("1003000001", "Sarah Brown", new BigDecimal("9500.25"), "SAVINGS", "EUR", southBranch),
            createAccount("1003000002", "David Miller", new BigDecimal("42000.00"), "CHECKING", "EUR", southBranch),
            
            // East Branch accounts
            createAccount("1004000001", "Lisa Anderson", new BigDecimal("6800.00"), "SAVINGS", "USD", eastBranch),
            createAccount("1004000002", "James Taylor", new BigDecimal("15200.80"), "CHECKING", "USD", eastBranch),
            
            // West Branch accounts
            createAccount("1005000001", "Jennifer Martinez", new BigDecimal("28500.00"), "SAVINGS", "USD", westBranch),
            createAccount("1005000002", "Christopher Garcia", new BigDecimal("11750.45"), "CHECKING", "USD", westBranch),
            createAccount("1005000003", "Amanda Robinson", new BigDecimal("95000.00"), "SAVINGS", "EUR", westBranch)
        );

        bankAccountRepository.saveAll(accounts);
        log.info("Seeded {} bank accounts", accounts.size());
    }

    private Branch createBranch(String name, String address, String phone, String code) {
        Branch branch = new Branch();
        branch.setName(name);
        branch.setAddress(address);
        branch.setPhone(phone);
        branch.setCode(code);
        return branch;
    }

    private BankAccount createAccount(String accountNumber, String accountHolder, BigDecimal balance,
                                       String accountType, String currency, Branch branch) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setAccountHolder(accountHolder);
        account.setBalance(balance);
        account.setAccountType(accountType);
        account.setCurrency(currency);
        account.setActive(true);
        account.setBranch(branch);
        return account;
    }
}
