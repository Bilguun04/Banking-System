/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package BankingSystem.core.intangableResources;
import java.util.*;

// line 45 "../../../../model.ump"
// line 103 "../../../../model.ump"
public class AccountType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AccountType Attributes
  private float monthlyFee;
  private float interestRate;

  //AccountType Associations
  private List<Privilege> privileges;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AccountType(float aMonthlyFee, float aInterestRate)
  {
    monthlyFee = aMonthlyFee;
    interestRate = aInterestRate;
    privileges = new ArrayList<Privilege>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMonthlyFee(float aMonthlyFee)
  {
    boolean wasSet = false;
    monthlyFee = aMonthlyFee;
    wasSet = true;
    return wasSet;
  }

  public boolean setInterestRate(float aInterestRate)
  {
    boolean wasSet = false;
    interestRate = aInterestRate;
    wasSet = true;
    return wasSet;
  }

  public float getMonthlyFee()
  {
    return monthlyFee;
  }

  public float getInterestRate()
  {
    return interestRate;
  }
  /* Code from template association_GetMany */
  public Privilege getPrivilege(int index)
  {
    Privilege aPrivilege = privileges.get(index);
    return aPrivilege;
  }

  public List<Privilege> getPrivileges()
  {
    List<Privilege> newPrivileges = Collections.unmodifiableList(privileges);
    return newPrivileges;
  }

  public int numberOfPrivileges()
  {
    int number = privileges.size();
    return number;
  }

  public boolean hasPrivileges()
  {
    boolean has = privileges.size() > 0;
    return has;
  }

  public int indexOfPrivilege(Privilege aPrivilege)
  {
    int index = privileges.indexOf(aPrivilege);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPrivileges()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPrivilege(Privilege aPrivilege)
  {
    boolean wasAdded = false;
    if (privileges.contains(aPrivilege)) { return false; }
    privileges.add(aPrivilege);
    if (aPrivilege.indexOfAccountType(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPrivilege.addAccountType(this);
      if (!wasAdded)
      {
        privileges.remove(aPrivilege);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePrivilege(Privilege aPrivilege)
  {
    boolean wasRemoved = false;
    if (!privileges.contains(aPrivilege))
    {
      return wasRemoved;
    }

    int oldIndex = privileges.indexOf(aPrivilege);
    privileges.remove(oldIndex);
    if (aPrivilege.indexOfAccountType(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPrivilege.removeAccountType(this);
      if (!wasRemoved)
      {
        privileges.add(oldIndex,aPrivilege);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPrivilegeAt(Privilege aPrivilege, int index)
  {  
    boolean wasAdded = false;
    if(addPrivilege(aPrivilege))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPrivileges()) { index = numberOfPrivileges() - 1; }
      privileges.remove(aPrivilege);
      privileges.add(index, aPrivilege);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePrivilegeAt(Privilege aPrivilege, int index)
  {
    boolean wasAdded = false;
    if(privileges.contains(aPrivilege))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPrivileges()) { index = numberOfPrivileges() - 1; }
      privileges.remove(aPrivilege);
      privileges.add(index, aPrivilege);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPrivilegeAt(aPrivilege, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Privilege> copyOfPrivileges = new ArrayList<Privilege>(privileges);
    privileges.clear();
    for(Privilege aPrivilege : copyOfPrivileges)
    {
      aPrivilege.removeAccountType(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "monthlyFee" + ":" + getMonthlyFee()+ "," +
            "interestRate" + ":" + getInterestRate()+ "]";
  }
}