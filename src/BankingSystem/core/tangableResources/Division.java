/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package BankingSystem.core.tangableResources;
import java.util.*;
import BankingSystem.core.humanResources.*;

// line 83 "../../../../model.ump"
// line 156 "../../../../model.ump"
public class Division
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Division Attributes
  private String name;

  //Division Associations
  private List<Employee> employees;
  private List<Division> subDivision;
  private Division division;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Division(String aName)
  {
    name = aName;
    employees = new ArrayList<Employee>();
    subDivision = new ArrayList<Division>();
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

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
  }
  /* Code from template association_GetMany */
  public Division getSubDivision(int index)
  {
    Division aSubDivision = subDivision.get(index);
    return aSubDivision;
  }

  public List<Division> getSubDivision()
  {
    List<Division> newSubDivision = Collections.unmodifiableList(subDivision);
    return newSubDivision;
  }

  public int numberOfSubDivision()
  {
    int number = subDivision.size();
    return number;
  }

  public boolean hasSubDivision()
  {
    boolean has = subDivision.size() > 0;
    return has;
  }

  public int indexOfSubDivision(Division aSubDivision)
  {
    int index = subDivision.indexOf(aSubDivision);
    return index;
  }
  /* Code from template association_GetOne */
  public Division getDivision()
  {
    return division;
  }

  public boolean hasDivision()
  {
    boolean has = division != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmployees()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Employee addEmployee(Person aPerson)
  {
    return new Employee(aPerson, this);
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    Division existingDivision = aEmployee.getDivision();
    boolean isNewDivision = existingDivision != null && !this.equals(existingDivision);
    if (isNewDivision)
    {
      aEmployee.setDivision(this);
    }
    else
    {
      employees.add(aEmployee);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmployee, as it must always have a division
    if (!this.equals(aEmployee.getDivision()))
    {
      employees.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSubDivision()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addSubDivision(Division aSubDivision)
  {
    boolean wasAdded = false;
    if (subDivision.contains(aSubDivision)) { return false; }
    Division existingDivision = aSubDivision.getDivision();
    if (existingDivision == null)
    {
      aSubDivision.setDivision(this);
    }
    else if (!this.equals(existingDivision))
    {
      existingDivision.removeSubDivision(aSubDivision);
      addSubDivision(aSubDivision);
    }
    else
    {
      subDivision.add(aSubDivision);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSubDivision(Division aSubDivision)
  {
    boolean wasRemoved = false;
    if (subDivision.contains(aSubDivision))
    {
      subDivision.remove(aSubDivision);
      aSubDivision.setDivision(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSubDivisionAt(Division aSubDivision, int index)
  {  
    boolean wasAdded = false;
    if(addSubDivision(aSubDivision))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubDivision()) { index = numberOfSubDivision() - 1; }
      subDivision.remove(aSubDivision);
      subDivision.add(index, aSubDivision);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubDivisionAt(Division aSubDivision, int index)
  {
    boolean wasAdded = false;
    if(subDivision.contains(aSubDivision))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubDivision()) { index = numberOfSubDivision() - 1; }
      subDivision.remove(aSubDivision);
      subDivision.add(index, aSubDivision);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubDivisionAt(aSubDivision, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setDivision(Division aDivision)
  {
    boolean wasSet = false;
    Division existingDivision = division;
    division = aDivision;
    if (existingDivision != null && !existingDivision.equals(aDivision))
    {
      existingDivision.removeSubDivision(this);
    }
    if (aDivision != null)
    {
      aDivision.addSubDivision(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=employees.size(); i > 0; i--)
    {
      Employee aEmployee = employees.get(i - 1);
      aEmployee.delete();
    }
    while( !subDivision.isEmpty() )
    {
      subDivision.get(0).setDivision(null);
    }
    if (division != null)
    {
      Division placeholderDivision = division;
      this.division = null;
      placeholderDivision.removeSubDivision(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}