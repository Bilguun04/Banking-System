/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package BankingSystem.core.humanResources;
import BankingSystem.core.tangableResources.*;

// line 16 "../../../../model.ump"
// line 137 "../../../../model.ump"
public class Employee extends PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Associations
  private Manager manager;
  private Division division;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(Person aPerson, Division aDivision)
  {
    super(aPerson);
    boolean didAddDivision = setDivision(aDivision);
    if (!didAddDivision)
    {
      throw new RuntimeException("Unable to create employee due to division. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Manager getManager()
  {
    return manager;
  }

  public boolean hasManager()
  {
    boolean has = manager != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Division getDivision()
  {
    return division;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setManager(Manager aManager)
  {
    boolean wasSet = false;
    Manager existingManager = manager;
    manager = aManager;
    if (existingManager != null && !existingManager.equals(aManager))
    {
      existingManager.removeEmployee(this);
    }
    if (aManager != null)
    {
      aManager.addEmployee(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setDivision(Division aDivision)
  {
    boolean wasSet = false;
    if (aDivision == null)
    {
      return wasSet;
    }

    Division existingDivision = division;
    division = aDivision;
    if (existingDivision != null && !existingDivision.equals(aDivision))
    {
      existingDivision.removeEmployee(this);
    }
    division.addEmployee(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (manager != null)
    {
      Manager placeholderManager = manager;
      this.manager = null;
      placeholderManager.removeEmployee(this);
    }
    Division placeholderDivision = division;
    this.division = null;
    if(placeholderDivision != null)
    {
      placeholderDivision.removeEmployee(this);
    }
    super.delete();
  }

}