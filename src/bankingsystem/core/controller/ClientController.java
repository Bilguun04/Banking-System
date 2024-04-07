package bankingsystem.core.controller;

import java.util.List;

import bankingsystem.core.application.BankApplication;
import bankingsystem.core.humanresources.*;
import bankingsystem.core.intangableresources.*;
import bankingsystem.core.tangableresources.*;

public class ClientController {
    public static Client createClient(Person person, String nameString, String address, String phoneNumber) {
        Client client = new Client(person, nameString, address, phoneNumber);
        return client;
    }

    public static Client getClient(int clientId) {
        return BankApplication.getBranch().getClient(clientId);
    }
}
