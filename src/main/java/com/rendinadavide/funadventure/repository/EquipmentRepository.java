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
    public Equipment save(Equipment equipment) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(equipment);
        tx.commit();
        return equipment;
    }

    @Override
    public Equipment findById(String Id) {
        return em.find(Equipment.class, Id);
    }

    @Override
    public List<Equipment> findAll() {
        return em.createQuery("Select e from Equipment e").getResultList();
    }

    @Override
    public void delete(Equipment equipment) {
        em.getTransaction().begin();
        em.remove(equipment);
        em.getTransaction().commit();
    }
}