package com.rendinadavide.assignment3.service;

import com.rendinadavide.assignment3.repository.model.Equipment;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class EquipmentService {

    @PersistenceContext
    private EntityManager em; //TODO sistemare creazione per tutti

    public EquipmentService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    public Equipment create(Date purchaseDate, String sn){
        Equipment newEquip = new Equipment(purchaseDate, sn);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(newEquip);
        tx.commit();
        return newEquip;
    }

    public void delete(Equipment equipment){
        em.getTransaction().begin();
        em.remove(equipment);
        em.getTransaction().commit();
    }

    public Equipment find(String equipmentId){
        return em.find(Equipment.class, equipmentId);
    }

    public List<Equipment> findAll(){
        return em.createQuery("Select e from Equipment e").getResultList();
    }

}
