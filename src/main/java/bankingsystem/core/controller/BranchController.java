package main.java.bankingsystem.core.controller;

import main.java.bankingsystem.core.application.*;
import main.java.bankingsystem.core.model.Branch;

public class BranchController {

    private static Branch branch = BankApplication.getBranch();

    public static Branch createBranch(String branchName, String branchAddress, String branchNumber) {
        branch = new Branch(branchName, branchAddress, branchNumber);
        return branch;
    }

    public Branch getBranch() {
        return branch;
    }

    public static boolean updateBranch(String branchName, String branchAddress, String branchNumber) {
        try {
            branch.setName(branchName);
            branch.setAddress(branchAddress);
            branch.setBranchNumber(branchNumber);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteBranch() {
        branch = null;
        return true;
    }
}