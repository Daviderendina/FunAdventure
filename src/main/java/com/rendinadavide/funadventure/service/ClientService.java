package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.repository.ClientRepository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(){
        clientRepository = new ClientRepository();
    }

    public Client create(String name, String surname, Date bDate){
        if(bDate.compareTo(new Date()) < 0){
            Client newClient = new Client(name, surname, bDate);
            clientRepository.save(newClient);
            return newClient;
        }
        return null;
    }

    public boolean addCompanion(Client client, Client companion){
        if(companion.calculateAge() >= 18 && !client.getId().equals(companion.getId())) {
            client.addCompanion(companion);
            return true;
        }
        return false;
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }

    public Client findById(String clientId){
        return clientRepository.findById(clientId);
    };

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

}
