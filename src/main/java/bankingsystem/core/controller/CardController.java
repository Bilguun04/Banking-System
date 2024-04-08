package main.java.bankingsystem.core.controller;

import java.util.List;

import main.java.bankingsystem.core.application.*;
import main.java.bankingsystem.core.model.Branch;
import main.java.bankingsystem.core.model.Card;
import main.java.bankingsystem.core.model.CreditCardAccount;


public class CardController {
    private static Branch branch = BankApplication.getBranch();

    public static Card createCard(String holderName, CreditCardAccount accountNumber) {
        Card card = new Card(holderName, accountNumber);
        return card;
    }

    public static Card getCard(int accountNumber, int cardId) {
        CreditCardAccount account = (CreditCardAccount) branch.getAccount(accountNumber);
        return account.getCard(cardId);
    }

    public static boolean updateCard(String holderName, CreditCardAccount creditCardAccount) {
        return creditCardAccount.getCard(0).setHolderName(holderName);
    }

    public static List<Card> getCards(int accountNumber) {
        CreditCardAccount account = (CreditCardAccount) branch.getAccount(accountNumber);
        return account.getCards();
    }
}
