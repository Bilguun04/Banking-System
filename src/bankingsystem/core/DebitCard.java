/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package src.bankingsystem.core;
import java.util.*;

// line 39 "../../../model.ump"
// line 111 "../../../model.ump"
public class DebitCard extends ReusableFinancialInstrument
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DebitCard Associations
  private List<BankAccount> bankAccounts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DebitCard(String aNumber, String aPin, Currency aCurrency, FinancialInstitution aFinancialInstitution, BankAccount... allBankAccounts)
  {
    super(aNumber, aPin, aCurrency, aFinancialInstitution);
    bankAccounts = new ArrayList<BankAccount>();
    boolean didAddBankAccounts = setBankAccounts(allBankAccounts);
    if (!didAddBankAccounts)
    {
      throw new RuntimeException("Unable to create DebitCard, must have at least 1 bankAccounts. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public BankAccount getBankAccount(int index)
  {
    BankAccount aBankAccount = bankAccounts.get(index);
    return aBankAccount;
  }

  public List<BankAccount> getBankAccounts()
  {
    List<BankAccount> newBankAccounts = Collections.unmodifiableList(bankAccounts);
    return newBankAccounts;
  }

  public int numberOfBankAccounts()
  {
    int number = bankAccounts.size();
    return number;
  }

  public boolean hasBankAccounts()
  {
    boolean has = bankAccounts.size() > 0;
    return has;
  }

  public int indexOfBankAccount(BankAccount aBankAccount)
  {
    int index = bankAccounts.indexOf(aBankAccount);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfBankAccountsValid()
  {
    boolean isValid = numberOfBankAccounts() >= minimumNumberOfBankAccounts();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBankAccounts()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBankAccount(BankAccount aBankAccount)
  {
    boolean wasAdded = false;
    if (bankAccounts.contains(aBankAccount)) { return false; }
    bankAccounts.add(aBankAccount);
    if (aBankAccount.indexOfDebitCard(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBankAccount.addDebitCard(this);
      if (!wasAdded)
      {
        bankAccounts.remove(aBankAccount);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeBankAccount(BankAccount aBankAccount)
  {
    boolean wasRemoved = false;
    if (!bankAccounts.contains(aBankAccount))
    {
      return wasRemoved;
    }

    if (numberOfBankAccounts() <= minimumNumberOfBankAccounts())
    {
      return wasRemoved;
    }

    int oldIndex = bankAccounts.indexOf(aBankAccount);
    bankAccounts.remove(oldIndex);
    if (aBankAccount.indexOfDebitCard(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBankAccount.removeDebitCard(this);
      if (!wasRemoved)
      {
        bankAccounts.add(oldIndex,aBankAccount);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setBankAccounts(BankAccount... newBankAccounts)
  {
    boolean wasSet = false;
    ArrayList<BankAccount> verifiedBankAccounts = new ArrayList<BankAccount>();
    for (BankAccount aBankAccount : newBankAccounts)
    {
      if (verifiedBankAccounts.contains(aBankAccount))
      {
        continue;
      }
      verifiedBankAccounts.add(aBankAccount);
    }

    if (verifiedBankAccounts.size() != newBankAccounts.length || verifiedBankAccounts.size() < minimumNumberOfBankAccounts())
    {
      return wasSet;
    }

    ArrayList<BankAccount> oldBankAccounts = new ArrayList<BankAccount>(bankAccounts);
    bankAccounts.clear();
    for (BankAccount aNewBankAccount : verifiedBankAccounts)
    {
      bankAccounts.add(aNewBankAccount);
      if (oldBankAccounts.contains(aNewBankAccount))
      {
        oldBankAccounts.remove(aNewBankAccount);
      }
      else
      {
        aNewBankAccount.addDebitCard(this);
      }
    }

    for (BankAccount anOldBankAccount : oldBankAccounts)
    {
      anOldBankAccount.removeDebitCard(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBankAccountAt(BankAccount aBankAccount, int index)
  {  
    boolean wasAdded = false;
    if(addBankAccount(aBankAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBankAccounts()) { index = numberOfBankAccounts() - 1; }
      bankAccounts.remove(aBankAccount);
      bankAccounts.add(index, aBankAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBankAccountAt(BankAccount aBankAccount, int index)
  {
    boolean wasAdded = false;
    if(bankAccounts.contains(aBankAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBankAccounts()) { index = numberOfBankAccounts() - 1; }
      bankAccounts.remove(aBankAccount);
      bankAccounts.add(index, aBankAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBankAccountAt(aBankAccount, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<BankAccount> copyOfBankAccounts = new ArrayList<BankAccount>(bankAccounts);
    bankAccounts.clear();
    for(BankAccount aBankAccount : copyOfBankAccounts)
    {
      aBankAccount.removeDebitCard(this);
    }
    super.delete();
  }

}