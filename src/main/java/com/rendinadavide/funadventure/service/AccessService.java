package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.domain.Access;
import com.rendinadavide.funadventure.domain.Equipment;
import com.rendinadavide.funadventure.repository.AccessRepository;

import java.util.List;
import java.util.Set;

public class AccessService {

    private AccessRepository accessRepository;

    public AccessService(){
        accessRepository = new AccessRepository();
    }

    public Access create(Set<Client> clientList, Set<Equipment> equipmentList) {

        List<String> activeClientsIds = accessRepository.findActiveClientsIds();
        long matchClient = equipmentList.stream().filter(client -> activeClientsIds.contains(client.getId())).count();
        if(matchClient > 0) return null;

        List<String> activeEquipIds = accessRepository.findActiveEquipmentIds();
        long matchEquip = equipmentList.stream().filter(equipment -> activeEquipIds.contains(equipment.getId())).count();
        if(matchEquip > 0) return null;

        Access access = new Access();
        access.setClientCollection(clientList);
        access.setEquipmentCollection(equipmentList);

        accessRepository.save(access);

        return access;
    }

    public Access findById(String Id) {
        return accessRepository.findById(Id);
    }

    public List<Access> findAll() {
        return accessRepository.findAll();
    }

    public void delete(Access access) {
        accessRepository.delete(access);
    }
}
