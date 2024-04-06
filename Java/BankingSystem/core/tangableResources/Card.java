/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package BankingSystem.core.tangableResources;
import BankingSystem.core.intangableResources.*;

// line 70 "../../../../model.ump"
// line 151 "../../../../model.ump"
public class Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Card Attributes
  private String holderName;

  //Card Associations
  private CreditCardAccount creditCardAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Card(String aHolderName, CreditCardAccount aCreditCardAccount)
  {
    holderName = aHolderName;
    boolean didAddCreditCardAccount = setCreditCardAccount(aCreditCardAccount);
    if (!didAddCreditCardAccount)
    {
      throw new RuntimeException("Unable to create card due to creditCardAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHolderName(String aHolderName)
  {
    boolean wasSet = false;
    holderName = aHolderName;
    wasSet = true;
    return wasSet;
  }

  public String getHolderName()
  {
    return holderName;
  }
  /* Code from template association_GetOne */
  public CreditCardAccount getCreditCardAccount()
  {
    return creditCardAccount;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setCreditCardAccount(CreditCardAccount aCreditCardAccount)
  {
    boolean wasSet = false;
    //Must provide creditCardAccount to card
    if (aCreditCardAccount == null)
    {
      return wasSet;
    }

    if (creditCardAccount != null && creditCardAccount.numberOfCards() <= CreditCardAccount.minimumNumberOfCards())
    {
      return wasSet;
    }

    CreditCardAccount existingCreditCardAccount = creditCardAccount;
    creditCardAccount = aCreditCardAccount;
    if (existingCreditCardAccount != null && !existingCreditCardAccount.equals(aCreditCardAccount))
    {
      boolean didRemove = existingCreditCardAccount.removeCard(this);
      if (!didRemove)
      {
        creditCardAccount = existingCreditCardAccount;
        return wasSet;
      }
    }
    creditCardAccount.addCard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    CreditCardAccount placeholderCreditCardAccount = creditCardAccount;
    this.creditCardAccount = null;
    if(placeholderCreditCardAccount != null)
    {
      placeholderCreditCardAccount.removeCard(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "holderName" + ":" + getHolderName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "creditCardAccount = "+(getCreditCardAccount()!=null?Integer.toHexString(System.identityHashCode(getCreditCardAccount())):"null");
  }
}