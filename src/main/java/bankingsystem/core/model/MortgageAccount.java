/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package main.java.bankingsystem.core.model;

// line 65 "../../../../model.ump"
// line 113 "../../../../model.ump"
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