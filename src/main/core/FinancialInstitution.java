package src.main.core;
import java.util.*;

public class FinancialInstitution
{

  private String name;

  private List<ReusableFinancialInstrument> reusableFinancialInstruments;

  public FinancialInstitution(String aName)
  {
    name = aName;
    reusableFinancialInstruments = new ArrayList<ReusableFinancialInstrument>();
  }

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
  public ReusableFinancialInstrument getReusableFinancialInstrument(int index)
  {
    ReusableFinancialInstrument aReusableFinancialInstrument = reusableFinancialInstruments.get(index);
    return aReusableFinancialInstrument;
  }

  public List<ReusableFinancialInstrument> getReusableFinancialInstruments()
  {
    List<ReusableFinancialInstrument> newReusableFinancialInstruments = Collections.unmodifiableList(reusableFinancialInstruments);
    return newReusableFinancialInstruments;
  }

  public int numberOfReusableFinancialInstruments()
  {
    int number = reusableFinancialInstruments.size();
    return number;
  }

  public boolean hasReusableFinancialInstruments()
  {
    boolean has = reusableFinancialInstruments.size() > 0;
    return has;
  }

  public int indexOfReusableFinancialInstrument(ReusableFinancialInstrument aReusableFinancialInstrument)
  {
    int index = reusableFinancialInstruments.indexOf(aReusableFinancialInstrument);
    return index;
  }
  public static int minimumNumberOfReusableFinancialInstruments()
  {
    return 0;
  }
  public ReusableFinancialInstrument addReusableFinancialInstrument(String aNumber, String aPin, Currency aCurrency)
  {
    return new ReusableFinancialInstrument(aNumber, aPin, aCurrency, this);
  }

  public boolean addReusableFinancialInstrument(ReusableFinancialInstrument aReusableFinancialInstrument)
  {
    boolean wasAdded = false;
    if (reusableFinancialInstruments.contains(aReusableFinancialInstrument)) { return false; }
    FinancialInstitution existingFinancialInstitution = aReusableFinancialInstrument.getFinancialInstitution();
    boolean isNewFinancialInstitution = existingFinancialInstitution != null && !this.equals(existingFinancialInstitution);
    if (isNewFinancialInstitution)
    {
      aReusableFinancialInstrument.setFinancialInstitution(this);
    }
    else
    {
      reusableFinancialInstruments.add(aReusableFinancialInstrument);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReusableFinancialInstrument(ReusableFinancialInstrument aReusableFinancialInstrument)
  {
    boolean wasRemoved = false;
    if (!this.equals(aReusableFinancialInstrument.getFinancialInstitution()))
    {
      reusableFinancialInstruments.remove(aReusableFinancialInstrument);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  public boolean addReusableFinancialInstrumentAt(ReusableFinancialInstrument aReusableFinancialInstrument, int index)
  {  
    boolean wasAdded = false;
    if(addReusableFinancialInstrument(aReusableFinancialInstrument))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReusableFinancialInstruments()) { index = numberOfReusableFinancialInstruments() - 1; }
      reusableFinancialInstruments.remove(aReusableFinancialInstrument);
      reusableFinancialInstruments.add(index, aReusableFinancialInstrument);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReusableFinancialInstrumentAt(ReusableFinancialInstrument aReusableFinancialInstrument, int index)
  {
    boolean wasAdded = false;
    if(reusableFinancialInstruments.contains(aReusableFinancialInstrument))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReusableFinancialInstruments()) { index = numberOfReusableFinancialInstruments() - 1; }
      reusableFinancialInstruments.remove(aReusableFinancialInstrument);
      reusableFinancialInstruments.add(index, aReusableFinancialInstrument);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReusableFinancialInstrumentAt(aReusableFinancialInstrument, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=reusableFinancialInstruments.size(); i > 0; i--)
    {
      ReusableFinancialInstrument aReusableFinancialInstrument = reusableFinancialInstruments.get(i - 1);
      aReusableFinancialInstrument.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}