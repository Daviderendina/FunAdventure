package com.rendinadavide.funadventure.repository;

import com.rendinadavide.funadventure.domain.Access;
import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.domain.Equipment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AccessRepository implements Repository<Access> {

    private EntityManager em;

    public AccessRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Override
    public Access save(Access access) {
        em.getTransaction().begin();
        em.persist(access);
        em.getTransaction().commit();
        return access;
    }

    @Override
    public Access findById(String Id) {
        return em.find(Access.class, Id);
    }

    @Override
    public List<Access> findAll() {
        return em.createQuery("Select a from Access a").getResultList();
    }

    @Override
    public void delete(Access access) {
        em.getTransaction().begin();
        em.remove(access);
        em.getTransaction().commit();
    }

    // TODO lascio con gli ID ?
    public List<String> findActiveEquipmentIds(){
        return em.createQuery("SELECT id from Access a join Access_Client ec on ec.Access_id = a.id WHERE a.exitDate is NULL").getResultList();
    }

    public List<String> findActiveClientsIds(){
        return em.createQuery("SELECT id from Access a join Access_Client ec on ec.Access_id = a.id WHERE a.exitDate is NULL").getResultList();
    }
}
