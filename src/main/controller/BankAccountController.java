package src.main.controller;

import java.sql.Date;

import src.main.core.*;

public class BankAccountController {
    private static BankAccount bankAccount;

    public static DebitCard createDebitCard(String cardNumber, String pin, Currency currency, FinancialInstitution financialInstitution) {
        DebitCard debitCard = new DebitCard(cardNumber, pin, currency, financialInstitution);
        return debitCard;
    }

    public static boolean addDebitCard(String cardNumber, String pin, Currency currency, FinancialInstitution financialInstitution) {
        DebitCard debitCard = createDebitCard(cardNumber, pin, currency, financialInstitution);
        try{
            bankAccount.addDebitCard(debitCard);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean addDebitCard(DebitCard debitCard) {
        try{
            bankAccount.addDebitCard(debitCard);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateDebitCard(DebitCard debitCard, String cardNumber, String pin, Currency currency, FinancialInstitution financialInstitution) {
        try{
            debitCard.setNumber(cardNumber);
            debitCard.setPin(pin);
            debitCard.setCurrency(currency);
            debitCard.setFinancialInstitution(financialInstitution);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean removeDebitCard(DebitCard debitCard) {
        try{
            bankAccount.removeDebitCard(debitCard);
            debitCard.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Cheque createCheque(String amount, Date date, String sequenceNumber) {
        Cheque cheque = new Cheque(amount, date, sequenceNumber, bankAccount);
        return cheque;
    }

    public static boolean addCheque(String amount, Date date, String sequenceNumber) {
        Cheque cheque = createCheque(amount, date, sequenceNumber);
        try{
            bankAccount.addCheque(cheque);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean addCheque(Cheque cheque) {
        try{
            bankAccount.addCheque(cheque);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean updateCheque(Cheque cheque, String amount, Date date, String sequenceNumber) {
        try{
            cheque.setAmount(amount);
            cheque.setDate(date);
            cheque.setSequenceNumber(sequenceNumber);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean removeCheque(Cheque cheque) {
        try{
            bankAccount.removeCheque(cheque);
            cheque.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
