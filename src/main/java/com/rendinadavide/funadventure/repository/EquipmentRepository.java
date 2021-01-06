package com.rendinadavide.funadventure.repository;

import com.rendinadavide.funadventure.domain.Equipment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EquipmentRepository implements Repository<Equipment> {

    private EntityManager em;

    public EquipmentRepository(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Override
    public boolean save(Equipment equipment) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(equipment);
        tx.commit();
        return true;
    }

    @Override
    public Equipment findById(String Id) {
        return em.find(Equipment.class, Id);
    }

    @Override
    public List<Equipment> findAll() {
        return em.createQuery("FROM Equipment").getResultList();
    }

    @Override
    public void delete(Equipment equipment) {
        //TODO bool anche qui?
        em.getTransaction().begin();
        em.remove(equipment);
        em.getTransaction().commit();
    }
}
