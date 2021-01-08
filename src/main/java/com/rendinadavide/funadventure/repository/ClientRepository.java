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
    public boolean save(Client client) {
        em.getTransaction().begin();
        try {
            em.persist(client);
            em.getTransaction().commit();

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        }
        return true;
    }

    @Override
    public Client findById(String Id) {
        return em.find(Client.class, Id);
    }

    @Override
    public List<Client> findAll() {
        return em.createQuery("FROM Client").getResultList();
    }

    @Override
    public void update(Client client) {
        em.getTransaction().begin();
        try {
            em.getTransaction().commit();

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Client client) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.remove(client);
            tx.commit();

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        }
    }
}
