package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.repository.Equipment;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class EquipmentService implements IEquipmentService {

    @PersistenceContext
    private EntityManager em;
    //TODO sistemare creazione per tutti con injection
    //TODO mancano i rollback delle transazioni

    public EquipmentService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Override
    public Equipment create(Date purchaseDate, String sn){
        Equipment newEquip = new Equipment(purchaseDate, sn);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(newEquip);
        tx.commit();
        return newEquip;
    }

    @Override
    public void delete(Equipment equipment){
        em.getTransaction().begin();
        em.remove(equipment);
        em.getTransaction().commit();
    }

    @Override
    public Equipment findById(String equipmentId){
        return em.find(Equipment.class, equipmentId);
    }

    public List<Equipment> findAll(){
        return em.createQuery("Select e from Equipment e").getResultList();
    }

}
