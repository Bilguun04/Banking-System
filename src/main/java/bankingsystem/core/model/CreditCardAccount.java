/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package main.java.bankingsystem.core.model;
import java.sql.Date;
import java.util.*;

// line 58 "../../../../model.ump"
// line 117 "../../../../model.ump"
public class CreditCardAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CreditCardAccount Attributes
  private Date expiryDate;

  //CreditCardAccount Associations
  private List<Card> cards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CreditCardAccount(int aAccountNumber, float aBalance, float aCreditLimit, AccountType aAccountType, Branch aBranch, Date aExpiryDate)
  {
    super(aAccountNumber, aBalance, aCreditLimit, aAccountType, aBranch);
    expiryDate = aExpiryDate;
    cards = new ArrayList<Card>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setExpiryDate(Date aExpiryDate)
  {
    boolean wasSet = false;
    expiryDate = aExpiryDate;
    wasSet = true;
    return wasSet;
  }

  public Date getExpiryDate()
  {
    return expiryDate;
  }
  /* Code from template association_GetMany */
  public Card getCard(int index)
  {
    Card aCard = cards.get(index);
    return aCard;
  }

  public List<Card> getCards()
  {
    List<Card> newCards = Collections.unmodifiableList(cards);
    return newCards;
  }

  public int numberOfCards()
  {
    int number = cards.size();
    return number;
  }

  public boolean hasCards()
  {
    boolean has = cards.size() > 0;
    return has;
  }

  public int indexOfCard(Card aCard)
  {
    int index = cards.indexOf(aCard);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfCardsValid()
  {
    boolean isValid = numberOfCards() >= minimumNumberOfCards();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCards()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Card addCard(String aHolderName)
  {
    Card aNewCard = new Card(aHolderName, this);
    return aNewCard;
  }

  public boolean addCard(Card aCard)
  {
    boolean wasAdded = false;
    if (cards.contains(aCard)) { return false; }
    CreditCardAccount existingCreditCardAccount = aCard.getCreditCardAccount();
    boolean isNewCreditCardAccount = existingCreditCardAccount != null && !this.equals(existingCreditCardAccount);

    if (isNewCreditCardAccount && existingCreditCardAccount.numberOfCards() <= minimumNumberOfCards())
    {
      return wasAdded;
    }
    if (isNewCreditCardAccount)
    {
      aCard.setCreditCardAccount(this);
    }
    else
    {
      cards.add(aCard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCard(Card aCard)
  {
    boolean wasRemoved = false;
    //Unable to remove aCard, as it must always have a creditCardAccount
    if (this.equals(aCard.getCreditCardAccount()))
    {
      return wasRemoved;
    }

    //creditCardAccount already at minimum (1)
    if (numberOfCards() <= minimumNumberOfCards())
    {
      return wasRemoved;
    }

    cards.remove(aCard);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCardAt(Card aCard, int index)
  {  
    boolean wasAdded = false;
    if(addCard(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCardAt(Card aCard, int index)
  {
    boolean wasAdded = false;
    if(cards.contains(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCardAt(aCard, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=cards.size(); i > 0; i--)
    {
      Card aCard = cards.get(i - 1);
      aCard.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "expiryDate" + "=" + (getExpiryDate() != null ? !getExpiryDate().equals(this)  ? getExpiryDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}