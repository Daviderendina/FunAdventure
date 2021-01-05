package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.repository.Client;
import com.rendinadavide.funadventure.repository.Entrance;
import com.rendinadavide.funadventure.repository.Equipment;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class EntranceService implements IEntranceService {

    @PersistenceContext
    EntityManager em;

    public EntranceService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Override
    public Entrance create(Set<Client> clientList, Set<Equipment> equipmentList) {
        em.getTransaction().begin();

        Entrance en = new Entrance();

        if(!checkNoActiveAccesses(clientList) || !checkEquipmentNotInUse(equipmentList)){
            em.getTransaction().rollback();
            return null;
        }

        en.setClientCollection(clientList);
        en.setEquipmentCollection(equipmentList);

        em.getTransaction().commit();

        return en;
    }

    @Override
    public Entrance findById(String Id) {
        return em.find(Entrance.class, Id);
    }

    @Override
    public List<Entrance> findAll() {
        //TODO
        return null;
    }

    //TODO per addPayment devo fare un metodo che chiude tutto? Secondo me sì, e poi chiamo pure la merge da dentro quel metodo

    @Override
    public void delete(Entrance entrance) {
        em.getTransaction().begin();
        em.remove(entrance);
        em.getTransaction().commit();
    }

    private boolean checkNoActiveAccesses(Set<Client> c){
        return em.createQuery("SELECT id from Entrance e join Entrance_Client ec on ec.Entrance_id = e.id WHERE e.exitDate is NULL and id in (:clientList)")
                .setParameter("clientList", c)
                .getResultList().size() == 0;
    }

    private boolean checkEquipmentNotInUse(Set<Equipment> e) {
        return em.createQuery("SELECT id from Entrance e join Entrance_Client ec on ec.Entrance_id = e.id WHERE e.exitDate is NULL and id in (:clientList)")
                .setParameter("clientList", e)
                .getResultList().size() == 0;
    }
}
