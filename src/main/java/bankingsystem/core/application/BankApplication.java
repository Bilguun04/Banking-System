package main.java.bankingsystem.core.application;

import main.java.bankingsystem.core.model.Branch;
import main.java.bankingsystem.core.model.humanresources.*;
import main.java.bankingsystem.core.model.intangableresources.*;
import main.java.bankingsystem.core.model.tangableresources.*;

public class BankApplication {
  private static Branch branch;

  public static void main(String[] args) {
    // TODO Start the application user interface here
  }

  public static Branch getBranch() {
    if (branch == null) {
      branch = new Branch(null, null, null);
    }
    return branch;
  }
}