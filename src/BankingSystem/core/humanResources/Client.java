/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package bankingsystem.core.humanresources;
import java.util.*;
import bankingsystem.core.intangableresources.*;

// line 22 "../../../../model.ump"
// line 122 "../../../../model.ump"
public class Client extends PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Client Attributes
  private String name;
  private String address;
  private String phoneNumber;

  //Client Associations
  private List<Account> accounts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Client(Person aPerson, String aName, String aAddress, String aPhoneNumber)
  {
    super(aPerson);
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    accounts = new ArrayList<Account>();
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

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
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

  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetMany */
  public Account getAccount(int index)
  {
    Account aAccount = accounts.get(index);
    return aAccount;
  }

  public List<Account> getAccounts()
  {
    List<Account> newAccounts = Collections.unmodifiableList(accounts);
    return newAccounts;
  }

  public int numberOfAccounts()
  {
    int number = accounts.size();
    return number;
  }

  public boolean hasAccounts()
  {
    boolean has = accounts.size() > 0;
    return has;
  }

  public int indexOfAccount(Account aAccount)
  {
    int index = accounts.indexOf(aAccount);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfAccountsValid()
  {
    boolean isValid = numberOfAccounts() >= minimumNumberOfAccounts();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAccounts()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAccount(Account aAccount)
  {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) { return false; }
    accounts.add(aAccount);
    if (aAccount.indexOfClient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAccount.addClient(this);
      if (!wasAdded)
      {
        accounts.remove(aAccount);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeAccount(Account aAccount)
  {
    boolean wasRemoved = false;
    if (!accounts.contains(aAccount))
    {
      return wasRemoved;
    }

    if (numberOfAccounts() <= minimumNumberOfAccounts())
    {
      return wasRemoved;
    }

    int oldIndex = accounts.indexOf(aAccount);
    accounts.remove(oldIndex);
    if (aAccount.indexOfClient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAccount.removeClient(this);
      if (!wasRemoved)
      {
        accounts.add(oldIndex,aAccount);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setAccounts(Account... newAccounts)
  {
    boolean wasSet = false;
    ArrayList<Account> verifiedAccounts = new ArrayList<Account>();
    for (Account aAccount : newAccounts)
    {
      if (verifiedAccounts.contains(aAccount))
      {
        continue;
      }
      verifiedAccounts.add(aAccount);
    }

    if (verifiedAccounts.size() != newAccounts.length || verifiedAccounts.size() < minimumNumberOfAccounts())
    {
      return wasSet;
    }

    ArrayList<Account> oldAccounts = new ArrayList<Account>(accounts);
    accounts.clear();
    for (Account aNewAccount : verifiedAccounts)
    {
      accounts.add(aNewAccount);
      if (oldAccounts.contains(aNewAccount))
      {
        oldAccounts.remove(aNewAccount);
      }
      else
      {
        aNewAccount.addClient(this);
      }
    }

    for (Account anOldAccount : oldAccounts)
    {
      anOldAccount.removeClient(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAccountAt(Account aAccount, int index)
  {  
    boolean wasAdded = false;
    if(addAccount(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAccountAt(Account aAccount, int index)
  {
    boolean wasAdded = false;
    if(accounts.contains(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAccountAt(aAccount, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Account> copyOfAccounts = new ArrayList<Account>(accounts);
    accounts.clear();
    for(Account aAccount : copyOfAccounts)
    {
      if (aAccount.numberOfClients() <= Account.minimumNumberOfClients())
      {
        aAccount.delete();
      }
      else
      {
        aAccount.removeClient(this);
      }
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}