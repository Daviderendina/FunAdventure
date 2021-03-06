package com.rendinadavide.funadventure.repository;

import com.rendinadavide.funadventure.domain.Equipment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class EquipmentRepository implements Repository<Equipment> {

    private EntityManager em;

    public EquipmentRepository(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    @Override
    public boolean save(Equipment equipment) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(equipment);
            tx.commit();
            return true;

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            return false;
        }
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
    public boolean update(Equipment equipment) {
        em.getTransaction().begin();
        try {
            em.getTransaction().commit();
            return true;

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Equipment equipment) {
        em.getTransaction().begin();
        try {
            em.remove(equipment);
            em.getTransaction().commit();
            return true;

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            return false;
        }
    }
}
