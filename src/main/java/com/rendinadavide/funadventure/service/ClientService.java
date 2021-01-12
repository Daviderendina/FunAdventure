package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.repository.AccessRepository;
import com.rendinadavide.funadventure.repository.ClientRepository;
import com.rendinadavide.funadventure.repository.RepositoryFactory;

import java.time.LocalDate;
import java.util.List;

public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(){
        clientRepository = (ClientRepository) RepositoryFactory.getRepository(RepositoryFactory.RepositoryEntityType.CLIENT);
    }

    public Client create(String name, String surname, LocalDate bDate){
        if(! validateDate(bDate)) return null;

        Client newClient = new Client(name, surname, bDate);
        clientRepository.save(newClient);
        return newClient;
    }

    public boolean addCompanion(Client client, Client companion){
        if(companion.calculateAge() >= 18 && !client.getId().equals(companion.getId())) {
            client.addCompanion(companion);

            clientRepository.update(client);

            return true;
        }
        return false;
    }

    public Client update(Client client, String name, String surname, LocalDate bDate){
        if(! validateDate(bDate)) return null;

        client.setName(name);
        client.setSurname(surname);
        client.setbDate(bDate);

        clientRepository.update(client);

        return client;
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

    /***
     * Returns true if the passed Date is valid past Date (past date than now)
     * @param d The date to check
     * @return True if the passed Date is valid.
     */
    private boolean validateDate(LocalDate d){
        return d.compareTo(LocalDate.now()) < 0;
    }

}
