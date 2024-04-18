package src.main.controller;

import src.main.core.*;

public class BankController {
    private static Bank bank;

    public static Branch createBranch(String name, String address) {
        Branch branch = new Branch(name, address, bank);
        return branch;
    }

    public static boolean addBranch(String name, String address) {
        Branch branch = new Branch(name, address, bank);
        try{
            bank.addBranch(branch);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean addBranch(Branch branch) {
        try{
            bank.addBranch(branch);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean removeBranch(Branch branch) {
        try{
            bank.removeBranch(branch);
            branch.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateBranch(Branch branch, String name, String address) {
        try{
            branch.setName(name);
            branch.setAddress(address);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}