package src.bankingsystem.controller;

import src.bankingsystem.core.*;

public class BankController {
    public BankController() {
    }

    public void createCreditCardCompany(String name) {
        CreditCardCompany creditCardCompany = new CreditCardCompany(name);
    }

    public void createCreditCard(String number, String pin, src.bankingsystem.core.Currency currency, src.bankingsystem.core.FinancialInstitution financialInstitution, String creditLimit, String name) {
        CreditCard creditCard = new CreditCard(number, pin, currency, financialInstitution, creditLimit, name);
    }

    public void createCurrency(String code, String exchangeRate) {
        Currency currency = new Currency(code, exchangeRate);
    }

    public void createFinancialInstrument() {
        FinancialInstrument financialInstrument = new FinancialInstrument();
    }
}