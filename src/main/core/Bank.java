package src.main.core;
import java.util.*;

public class Bank extends FinancialInstitution
{

  private List<Branch> branchs;

  public Bank(String aName)
  {
    super(aName);
    branchs = new ArrayList<Branch>();
  }

  public Branch getBranch(int index)
  {
    Branch aBranch = branchs.get(index);
    return aBranch;
  }

  public List<Branch> getBranchs()
  {
    List<Branch> newBranchs = Collections.unmodifiableList(branchs);
    return newBranchs;
  }

  public int numberOfBranchs()
  {
    int number = branchs.size();
    return number;
  }

  public boolean hasBranchs()
  {
    boolean has = branchs.size() > 0;
    return has;
  }

  public int indexOfBranch(Branch aBranch)
  {
    int index = branchs.indexOf(aBranch);
    return index;
  }
  public static int minimumNumberOfBranchs()
  {
    return 0;
  }
  public Branch addBranch(String aName, String aAddress)
  {
    return new Branch(aName, aAddress, this);
  }

  public boolean addBranch(Branch aBranch)
  {
    boolean wasAdded = false;
    if (branchs.contains(aBranch)) { return false; }
    Bank existingBank = aBranch.getBank();
    boolean isNewBank = existingBank != null && !this.equals(existingBank);
    if (isNewBank)
    {
      aBranch.setBank(this);
    }
    else
    {
      branchs.add(aBranch);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBranch(Branch aBranch)
  {
    boolean wasRemoved = false;
    if (!this.equals(aBranch.getBank()))
    {
      branchs.remove(aBranch);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  public boolean addBranchAt(Branch aBranch, int index)
  {  
    boolean wasAdded = false;
    if(addBranch(aBranch))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBranchs()) { index = numberOfBranchs() - 1; }
      branchs.remove(aBranch);
      branchs.add(index, aBranch);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBranchAt(Branch aBranch, int index)
  {
    boolean wasAdded = false;
    if(branchs.contains(aBranch))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBranchs()) { index = numberOfBranchs() - 1; }
      branchs.remove(aBranch);
      branchs.add(index, aBranch);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBranchAt(aBranch, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=branchs.size(); i > 0; i--)
    {
      Branch aBranch = branchs.get(i - 1);
      aBranch.delete();
    }
    super.delete();
  }

}