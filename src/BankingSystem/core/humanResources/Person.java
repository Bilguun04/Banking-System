/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package bankingsystem.core.humanresources;
import java.util.*;

// line 8 "../../../../model.ump"
// line 128 "../../../../model.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;
  private String address;
  private String phoneNumber;
  private String email;

  //Person Associations
  private List<PersonRole> personRoles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, String aAddress, String aPhoneNumber, String aEmail)
  {
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    email = aEmail;
    personRoles = new ArrayList<PersonRole>();
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

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
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

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetMany */
  public PersonRole getPersonRole(int index)
  {
    PersonRole aPersonRole = personRoles.get(index);
    return aPersonRole;
  }

  public List<PersonRole> getPersonRoles()
  {
    List<PersonRole> newPersonRoles = Collections.unmodifiableList(personRoles);
    return newPersonRoles;
  }

  public int numberOfPersonRoles()
  {
    int number = personRoles.size();
    return number;
  }

  public boolean hasPersonRoles()
  {
    boolean has = personRoles.size() > 0;
    return has;
  }

  public int indexOfPersonRole(PersonRole aPersonRole)
  {
    int index = personRoles.indexOf(aPersonRole);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersonRoles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PersonRole addPersonRole()
  {
    return new PersonRole(this);
  }

  public boolean addPersonRole(PersonRole aPersonRole)
  {
    boolean wasAdded = false;
    if (personRoles.contains(aPersonRole)) { return false; }
    Person existingPerson = aPersonRole.getPerson();
    boolean isNewPerson = existingPerson != null && !this.equals(existingPerson);
    if (isNewPerson)
    {
      aPersonRole.setPerson(this);
    }
    else
    {
      personRoles.add(aPersonRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePersonRole(PersonRole aPersonRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aPersonRole, as it must always have a person
    if (!this.equals(aPersonRole.getPerson()))
    {
      personRoles.remove(aPersonRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPersonRoleAt(PersonRole aPersonRole, int index)
  {  
    boolean wasAdded = false;
    if(addPersonRole(aPersonRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersonRoles()) { index = numberOfPersonRoles() - 1; }
      personRoles.remove(aPersonRole);
      personRoles.add(index, aPersonRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonRoleAt(PersonRole aPersonRole, int index)
  {
    boolean wasAdded = false;
    if(personRoles.contains(aPersonRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersonRoles()) { index = numberOfPersonRoles() - 1; }
      personRoles.remove(aPersonRole);
      personRoles.add(index, aPersonRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonRoleAt(aPersonRole, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=personRoles.size(); i > 0; i--)
    {
      PersonRole aPersonRole = personRoles.get(i - 1);
      aPersonRole.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "email" + ":" + getEmail()+ "]";
  }
}