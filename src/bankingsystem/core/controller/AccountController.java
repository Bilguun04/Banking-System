package bankingsystem.core.controller;

import bankingsystem.core.humanresources.*;
import bankingsystem.core.intangableresources.*;
import bankingsystem.core.tangableresources.*;
import bankingsystem.core.application.*;

import java.util.List;

public class AccountController {

    private static Branch branch = BranchApplication.getBranch();

    public static boolean createAccount(int accountNumber, float balance, float creditLimit, AccountType accountType) {
        Account account = new Account(accountNumber, balance, creditLimit, accountType, branch);
        branch.addAccount(account);
        return true;
    }

    public static Account getAccount(int accountNumber) {
        return branch.getAccount(accountNumber);
    }

    public static boolean updateAccount(int accountNumber, float balance, float creditLimit, AccountType accountType) {
        Account account = branch.getAccount(accountNumber);
        account.setBalance(balance);
        account.setCreditLimit(creditLimit);
        account.setAccountType(accountType);
        return true;
    }

    public static boolean deleteAccount(int accountNumber) {
        Account account = branch.getAccount(accountNumber);
        branch.removeAccount(account);
        return true;
    }

    public static boolean addClientToAccount(int accountNumber, Client client) {
        Account account = branch.getAccount(accountNumber);
        account.addClient(client);
        return true;
    }

    public static boolean removeClientFromAccount(int accountNumber, Client client) {
        Account account = branch.getAccount(accountNumber);
        account.removeClient(client);
        return true;
    }

    public static boolean transferMoney(int fromAccountNumber, int toAccountNumber, float amount) {
        Account fromAccount = branch.getAccount(fromAccountNumber);
        Account toAccount = branch.getAccount(toAccountNumber);
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        return true;
    }

    public static boolean depositMoney(int accountNumber, float amount) {
        Account account = branch.getAccount(accountNumber);
        account.setBalance(account.getBalance() + amount);
        return true;
    }

    public static boolean withdrawMoney(int accountNumber, float amount) {
        Account account = branch.getAccount(accountNumber);
        account.setBalance(account.getBalance() - amount);
        return true;
    }

    public static List<Account> getAccounts() {
        return branch.getAccounts();
    }
}
