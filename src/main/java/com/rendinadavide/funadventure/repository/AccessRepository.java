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
        try {
            em.persist(access);
            em.getTransaction().commit();
            return true;

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            return false;
        }
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
    public boolean delete(Access access) {
        em.getTransaction().begin();
        try {
            em.remove(access);
            em.getTransaction().commit();
            return true;

        } catch (RuntimeException ex){
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean update(Access access){
        em.getTransaction().begin();
        try {
            em.getTransaction().commit();
            return true;

        } catch (RuntimeException ex){
            em.getTransaction().rollback();
            return false;
        }
    }

    public List<Equipment> findEquipmentInUse(){
        return em.createQuery("Select ec from Access a join a.equipmentCollection ec where exitDate is null").getResultList();
    }

    public List<Client> findClientWithActiveAccess(){
        return em.createQuery("Select c from Access a join a.clientCollection c where exitDate is null").getResultList();
    }

    public List<Client> findClientFreeAccess(){
        return em.createQuery("Select c from Access a join a.clientCollection c join a.payment p where p.amount < 0").getResultList();
    }
}
