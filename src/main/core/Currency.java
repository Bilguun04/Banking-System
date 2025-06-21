package src.main.core;

public class Currency
{
  private String code;
  private String exchangeRate;

  public Currency(String aCode, String aExchangeRate)
  {
    code = aCode;
    exchangeRate = aExchangeRate;
  }

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