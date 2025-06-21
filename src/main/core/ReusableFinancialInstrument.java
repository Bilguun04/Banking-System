package src.main.core;

public class ReusableFinancialInstrument extends FinancialInstrument
{
  private String number;
  private String pin;

  private Currency currency;
  private FinancialInstitution financialInstitution;

  public ReusableFinancialInstrument(String aNumber, String aPin, Currency aCurrency, FinancialInstitution aFinancialInstitution)
  {
    super();
    number = aNumber;
    pin = aPin;
    if (!setCurrency(aCurrency))
    {
      throw new RuntimeException("Unable to create ReusableFinancialInstrument due to aCurrency. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFinancialInstitution = setFinancialInstitution(aFinancialInstitution);
    if (!didAddFinancialInstitution)
    {
      throw new RuntimeException("Unable to create reusableFinancialInstrument due to financialInstitution. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public boolean setNumber(String aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setPin(String aPin)
  {
    boolean wasSet = false;
    pin = aPin;
    wasSet = true;
    return wasSet;
  }

  public String getNumber()
  {
    return number;
  }

  public String getPin()
  {
    return pin;
  }
  public Currency getCurrency()
  {
    return currency;
  }
  public FinancialInstitution getFinancialInstitution()
  {
    return financialInstitution;
  }
  public boolean setCurrency(Currency aNewCurrency)
  {
    boolean wasSet = false;
    if (aNewCurrency != null)
    {
      currency = aNewCurrency;
      wasSet = true;
    }
    return wasSet;
  }
  public boolean setFinancialInstitution(FinancialInstitution aFinancialInstitution)
  {
    boolean wasSet = false;
    if (aFinancialInstitution == null)
    {
      return wasSet;
    }

    FinancialInstitution existingFinancialInstitution = financialInstitution;
    financialInstitution = aFinancialInstitution;
    if (existingFinancialInstitution != null && !existingFinancialInstitution.equals(aFinancialInstitution))
    {
      existingFinancialInstitution.removeReusableFinancialInstrument(this);
    }
    financialInstitution.addReusableFinancialInstrument(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    currency = null;
    FinancialInstitution placeholderFinancialInstitution = financialInstitution;
    this.financialInstitution = null;
    if(placeholderFinancialInstitution != null)
    {
      placeholderFinancialInstitution.removeReusableFinancialInstrument(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "pin" + ":" + getPin()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "currency = "+(getCurrency()!=null?Integer.toHexString(System.identityHashCode(getCurrency())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "financialInstitution = "+(getFinancialInstitution()!=null?Integer.toHexString(System.identityHashCode(getFinancialInstitution())):"null");
  }
}