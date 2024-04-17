/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package BankingSystem.core;
import java.util.*;

// line 73 "../../../model.ump"
// line 106 "../../../model.ump"
public class Loan extends BankAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Loan(String aAccountNumber, String aBalance, float aOverdraftOrCreditLimit, Branch aBranch)
  {
    super(aAccountNumber, aBalance, aOverdraftOrCreditLimit, aBranch);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}