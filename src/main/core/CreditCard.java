package src.main.core;

public class CreditCard extends ReusableFinancialInstrument
{

  private String creditLimit;
  private String name;

  public CreditCard(String aNumber, String aPin, Currency aCurrency, FinancialInstitution aFinancialInstitution, String aCreditLimit, String aName)
  {
    super(aNumber, aPin, aCurrency, aFinancialInstitution);
    creditLimit = aCreditLimit;
    name = aName;
  }

  public boolean setCreditLimit(String aCreditLimit)
  {
    boolean wasSet = false;
    creditLimit = aCreditLimit;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getCreditLimit()
  {
    return creditLimit;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "creditLimit" + ":" + getCreditLimit()+ "," +
            "name" + ":" + getName()+ "]";
  }
}