/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package bankingsystem.core.humanresources;

// line 6 "../../../../model.ump"
// line 142 "../../../../model.ump"
public class PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PersonRole Associations
  private Person person;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PersonRole(Person aPerson)
  {
    boolean didAddPerson = setPerson(aPerson);
    if (!didAddPerson)
    {
      throw new RuntimeException("Unable to create personRole due to person. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Person getPerson()
  {
    return person;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPerson(Person aPerson)
  {
    boolean wasSet = false;
    if (aPerson == null)
    {
      return wasSet;
    }

    Person existingPerson = person;
    person = aPerson;
    if (existingPerson != null && !existingPerson.equals(aPerson))
    {
      existingPerson.removePersonRole(this);
    }
    person.addPersonRole(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Person placeholderPerson = person;
    this.person = null;
    if(placeholderPerson != null)
    {
      placeholderPerson.removePersonRole(this);
    }
  }

}