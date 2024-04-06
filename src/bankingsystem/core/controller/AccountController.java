package bankingsystem.core.controller;

import bankingsystem.core.humanresources.*;
import bankingsystem.core.intangableresources.*;
import bankingsystem.core.tangableresources.*;
import bankingsystem.core.application.*;

public class AccountController {

    private static Branch branch = BranchApplication.getBranch();
    
    public static boolean addAccount(String accountNumber, String accountType, double balance, String branchName) {
        Account account = new Account(accountNumber, accountType, balance);
        branch.addAccount(account);
        return true;
    }
}
