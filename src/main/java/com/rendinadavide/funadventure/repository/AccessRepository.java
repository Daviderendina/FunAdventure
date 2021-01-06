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
    public boolean save(Access access) {
        em.getTransaction().begin();
        em.persist(access);
        if(access.getPayment() != null) em.persist(access.getPayment());
        em.getTransaction().commit();
        return true;
    }

    @Override
    public Access findById(String Id) {
        return em.find(Access.class, Id);
    }

    @Override
    public List<Access> findAll() {
        return em.createQuery("FROM Access").getResultList();
    }

    @Override
    public void delete(Access access) {
        em.getTransaction().begin();
        em.remove(access);
        em.getTransaction().commit();
    }

    public void update(Access access){
        em.getTransaction().begin();
        if(access.getPayment() != null) em.persist(access.getPayment());
        Access a = em.merge(access);
        em.flush();
        em.getTransaction().commit();
    }

    public List<Equipment> findEquipmentInUse(){
        return em.createQuery("Select ec from Access a join a.equipmentCollection ec where exitDate is null").getResultList();
    }

    public List<Client> findClientWithActiveAccess(){
        return em.createQuery("Select c from Access a join a.clientCollection c where exitDate is null").getResultList();
    }
}
