/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package src.main.core;
import java.sql.Date;

/**
 * Positioning
 */
// line 43 "../../../model.ump"
// line 83 "../../../model.ump"
public class Cheque extends FinancialInstrument
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Cheque Attributes
  private String amount;
  private Date date;
  private String sequenceNumber;

  //Cheque Associations
  private BankAccount bankAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Cheque(String aAmount, Date aDate, String aSequenceNumber, BankAccount aBankAccount)
  {
    super();
    amount = aAmount;
    date = aDate;
    sequenceNumber = aSequenceNumber;
    boolean didAddBankAccount = setBankAccount(aBankAccount);
    if (!didAddBankAccount)
    {
      throw new RuntimeException("Unable to create cheque due to bankAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAmount(String aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setSequenceNumber(String aSequenceNumber)
  {
    boolean wasSet = false;
    sequenceNumber = aSequenceNumber;
    wasSet = true;
    return wasSet;
  }

  public String getAmount()
  {
    return amount;
  }

  public Date getDate()
  {
    return date;
  }

  public String getSequenceNumber()
  {
    return sequenceNumber;
  }
  /* Code from template association_GetOne */
  public BankAccount getBankAccount()
  {
    return bankAccount;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBankAccount(BankAccount aBankAccount)
  {
    boolean wasSet = false;
    if (aBankAccount == null)
    {
      return wasSet;
    }

    BankAccount existingBankAccount = bankAccount;
    bankAccount = aBankAccount;
    if (existingBankAccount != null && !existingBankAccount.equals(aBankAccount))
    {
      existingBankAccount.removeCheque(this);
    }
    bankAccount.addCheque(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BankAccount placeholderBankAccount = bankAccount;
    this.bankAccount = null;
    if(placeholderBankAccount != null)
    {
      placeholderBankAccount.removeCheque(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "amount" + ":" + getAmount()+ "," +
            "sequenceNumber" + ":" + getSequenceNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "bankAccount = "+(getBankAccount()!=null?Integer.toHexString(System.identityHashCode(getBankAccount())):"null");
  }
}