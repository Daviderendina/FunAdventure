package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.domain.Equipment;
import com.rendinadavide.funadventure.repository.AccessRepository;
import com.rendinadavide.funadventure.repository.EquipmentRepository;
import com.rendinadavide.funadventure.repository.RepositoryFactory;

import java.time.LocalDate;
import java.util.List;

public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    public EquipmentService(){
        equipmentRepository = (EquipmentRepository) RepositoryFactory.getRepository(RepositoryFactory.RepositoryEntityType.EQUIPMENT);
    }

    public Equipment create(LocalDate purchaseDate, String sn){
        Equipment newEquip = new Equipment(purchaseDate, sn);
        equipmentRepository.save(newEquip);
        return newEquip;
    }

    public Equipment update(Equipment equipment, LocalDate newDate, String newSn){
        if(newDate.compareTo(LocalDate.now()) >= 0) return null;
        equipment.setPurchaseDate(newDate);
        equipment.setSerialNumber(newSn);

        equipmentRepository.update(equipment);

        return equipment;
    }

    public Equipment findById(String equipmentId){
        return equipmentRepository.findById(equipmentId);
    }

    public List<Equipment> findAll(){
        return equipmentRepository.findAll();
    }

    public void delete(Equipment equipment){
        equipmentRepository.delete(equipment);
    }
}
