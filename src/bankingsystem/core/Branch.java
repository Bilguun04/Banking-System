/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package src.bankingsystem.core;
import java.util.*;

// line 66 "../../../model.ump"
// line 139 "../../../model.ump"
public class Branch
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Branch Attributes
  private String name;
  private String address;

  //Branch Associations
  private List<BankAccount> bankAccounts;
  private Bank bank;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Branch(String aName, String aAddress, Bank aBank)
  {
    name = aName;
    address = aAddress;
    bankAccounts = new ArrayList<BankAccount>();
    boolean didAddBank = setBank(aBank);
    if (!didAddBank)
    {
      throw new RuntimeException("Unable to create branch due to bank. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }
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
  /* Code from template association_GetOne */
  public Bank getBank()
  {
    return bank;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBankAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BankAccount addBankAccount(String aAccountNumber, String aBalance, float aOverdraftOrCreditLimit)
  {
    return new BankAccount(aAccountNumber, aBalance, aOverdraftOrCreditLimit, this);
  }

  public boolean addBankAccount(BankAccount aBankAccount)
  {
    boolean wasAdded = false;
    if (bankAccounts.contains(aBankAccount)) { return false; }
    Branch existingBranch = aBankAccount.getBranch();
    boolean isNewBranch = existingBranch != null && !this.equals(existingBranch);
    if (isNewBranch)
    {
      aBankAccount.setBranch(this);
    }
    else
    {
      bankAccounts.add(aBankAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBankAccount(BankAccount aBankAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aBankAccount, as it must always have a branch
    if (!this.equals(aBankAccount.getBranch()))
    {
      bankAccounts.remove(aBankAccount);
      wasRemoved = true;
    }
    return wasRemoved;
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
  /* Code from template association_SetOneToMany */
  public boolean setBank(Bank aBank)
  {
    boolean wasSet = false;
    if (aBank == null)
    {
      return wasSet;
    }

    Bank existingBank = bank;
    bank = aBank;
    if (existingBank != null && !existingBank.equals(aBank))
    {
      existingBank.removeBranch(this);
    }
    bank.addBranch(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=bankAccounts.size(); i > 0; i--)
    {
      BankAccount aBankAccount = bankAccounts.get(i - 1);
      aBankAccount.delete();
    }
    Bank placeholderBank = bank;
    this.bank = null;
    if(placeholderBank != null)
    {
      placeholderBank.removeBranch(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bank = "+(getBank()!=null?Integer.toHexString(System.identityHashCode(getBank())):"null");
  }
}