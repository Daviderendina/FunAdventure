package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.repository.Client;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class ClientService implements IClientService {

    @PersistenceContext
    private EntityManager em;

    public ClientService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Override
    public Client create(String name, String surname, Date bDate){
        Client newClient = new Client(name, surname, bDate);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(newClient);
        tx.commit();
        return newClient;
    }

    public boolean addCompanion(Client client, Client companion){
        if(companion.calculateAge() >= 18 && client.getId() != companion.getId()) {
            client.addCompanion(companion);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Client client) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(client);
        tx.commit();
    }

    @Override
    public Client findById(String clientId){
        return em.find(Client.class, clientId);
    };

    @Override
    public List<Client> findAll(){
        return em.createQuery("Select c from Client c").getResultList();
    }

}
