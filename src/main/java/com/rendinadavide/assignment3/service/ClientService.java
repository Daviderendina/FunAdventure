package com.rendinadavide.assignment3.service;

import com.rendinadavide.assignment3.repository.model.Client;

import java.util.Date;

public class ClientService {

    public Client createClient(String name, String surname, Date bDate){
        Client cli = new Client(name, surname, bDate);
        // entityManager.persist(cli)
        return cli;
    }

    public boolean addCompanion(Client client, Client companion){
        return client.addCompanion(companion);
    }

    public void removeClient(){}; //TODO stacca dal manager

    public void updateClient(){/*TODO va fatto l'update una volta che è legato dall'EM ?*/};

    public void findClient(){}; //TODO Devo fare tutte le varie find
}
