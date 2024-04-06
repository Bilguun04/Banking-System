/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package BankingSystem.core.intangableResources;
import java.util.*;
import BankingSystem.core.humanResources.*;
import BankingSystem.core.tangableResources.*;

// line 63 "../../../../model.ump"
// line 111 "../../../../model.ump"
public class MortgageAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MortgageAccount Attributes
  private String collateral;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MortgageAccount(int aAccountNumber, float aBalance, float aCreditLimit, AccountType aAccountType, Branch aBranch, String aCollateral)
  {
    super(aAccountNumber, aBalance, aCreditLimit, aAccountType, aBranch);
    collateral = aCollateral;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCollateral(String aCollateral)
  {
    boolean wasSet = false;
    collateral = aCollateral;
    wasSet = true;
    return wasSet;
  }

  public String getCollateral()
  {
    return collateral;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "collateral" + ":" + getCollateral()+ "]";
  }
}