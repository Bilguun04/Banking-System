/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package BankingSystem.core;

// line 60 "../../../model.ump"
// line 94 "../../../model.ump"
public class Currency
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Currency Attributes
  private String code;
  private String exchangeRate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Currency(String aCode, String aExchangeRate)
  {
    code = aCode;
    exchangeRate = aExchangeRate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    code = aCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setExchangeRate(String aExchangeRate)
  {
    boolean wasSet = false;
    exchangeRate = aExchangeRate;
    wasSet = true;
    return wasSet;
  }

  public String getCode()
  {
    return code;
  }

  public String getExchangeRate()
  {
    return exchangeRate;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "code" + ":" + getCode()+ "," +
            "exchangeRate" + ":" + getExchangeRate()+ "]";
  }
}