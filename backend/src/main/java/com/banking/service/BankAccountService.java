package com.banking.service;

import com.banking.entity.BankAccount;
import com.banking.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    
    @Autowired
    private BankAccountRepository bankAccountRepository;
    
    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }
    
    public Optional<BankAccount> getAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }
    
    public BankAccount createAccount(BankAccount account) {
        return bankAccountRepository.save(account);
    }
    
    public BankAccount updateAccount(Long id, BankAccount accountDetails) {
        Optional<BankAccount> account = bankAccountRepository.findById(id);
        if (account.isPresent()) {
            BankAccount existingAccount = account.get();
            existingAccount.setAccountHolder(accountDetails.getAccountHolder());
            existingAccount.setBalance(accountDetails.getBalance());
            existingAccount.setActive(accountDetails.getActive());
            return bankAccountRepository.save(existingAccount);
        }
        return null;
    }
    
    public void deleteAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
    
    public List<BankAccount> getAccountsByBranch(Long branchId) {
        return bankAccountRepository.findByBranchId(branchId);
    }
}
