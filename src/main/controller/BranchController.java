package src.main.controller;

import src.main.core.*;

public class BranchController {
    private static Branch branch;

    public static BankAccount createBankAccount(String accountNumber, String balance, float overdraftOrCreditLimit) {
        BankAccount bankAccount = new BankAccount(accountNumber, balance, overdraftOrCreditLimit, branch);
        return bankAccount;
    }

    public static boolean addBankAccount(String accountNumber, String accountType, double balance) {
        BankAccount bankAccount = createBankAccount(accountNumber, accountType, 0);
        try{
            branch.addBankAccount(bankAccount);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean addBankAccount(BankAccount bankAccount) {
        try{
            branch.addBankAccount(bankAccount);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean removeBankAccount(BankAccount bankAccount) {
        try{
            branch.removeBankAccount(bankAccount);
            bankAccount.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateBankAccount(BankAccount bankAccount, String accountNumber, String balance, float overdraftOrCreditLimit) {
        try{
            bankAccount.setAccountNumber(accountNumber);
            bankAccount.setBalance(balance);
            bankAccount.setOverdraftOrCreditLimit(overdraftOrCreditLimit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
