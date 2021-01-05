package com.rendinadavide.funadventure.repository;

import com.rendinadavide.funadventure.domain.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ClientRepository implements Repository<Client> {

    private EntityManager em;

    public ClientRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }


    @Override
    public Client save(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        return client;
    }

    @Override
    public Client findById(String Id) {
        return em.find(Client.class, Id);
    }

    @Override
    public List<Client> findAll() {
        return em.createQuery("Select c from Client c").getResultList();
    }

    @Override
    public void delete(Client client) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(client);
        tx.commit();
    }
}