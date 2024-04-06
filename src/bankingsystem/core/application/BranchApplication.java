package bankingsystem.core.application;

import bankingsystem.core.tangableresources.*;
import bankingsystem.core.intangableresources.*;
import bankingsystem.core.humanresources.*;

public class BranchApplication {
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