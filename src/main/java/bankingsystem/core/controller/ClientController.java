package main.java.bankingsystem.core.controller;

import java.util.List;

import main.java.bankingsystem.core.application.BankApplication;
import main.java.bankingsystem.core.model.Client;
import main.java.bankingsystem.core.model.Person;

public class ClientController {
    public static Client createClient(Person person, String nameString, String address, String phoneNumber) {
        Client client = new Client(person, nameString, address, phoneNumber);
        return client;
    }

    public static Client getClient(int clientId) {
        return BankApplication.getBranch().getClient(clientId);
    }
}
