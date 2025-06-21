package src.main.core;

public class Loan extends BankAccount
{

  public Loan(String aAccountNumber, String aBalance, float aOverdraftOrCreditLimit, Branch aBranch)
  {
    super(aAccountNumber, aBalance, aOverdraftOrCreditLimit, aBranch);
  }

  public void delete()
  {
    super.delete();
  }

}