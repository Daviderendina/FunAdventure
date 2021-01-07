package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.domain.Access;
import com.rendinadavide.funadventure.domain.Equipment;
import com.rendinadavide.funadventure.domain.payment.Payment;
import com.rendinadavide.funadventure.repository.AccessRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AccessService {

    private AccessRepository accessRepository;

    public AccessService(){
        accessRepository = new AccessRepository();
    }

    public Access create(List<Client> clientList, List<Equipment> equipmentList) {

        //TODO vedere se funzia
        if(equipmentList != null && equipmentList.size() > 0) {
            List<Equipment> equipmentInUse = findEquipmentInUse();
            //long matchEquip = equipmentList.stream().filter(equipment -> equipmentInUse.contains(equipment)).count();
            //if(matchEquip > 0) return null;
            equipmentInUse.retainAll(equipmentList);
            if (equipmentInUse.size() > 0) return null;
        }

        if(clientList != null && clientList.size() > 0) {
            List<Client> clientWithActiveAccess = findClientWithActiveAccess();
            //long matchClient = equipmentList.stream().filter(client -> clientWithActiveAccess.contains(client)).count();
            //if(matchClient > 0) return null;
            clientWithActiveAccess.retainAll(clientList);
            if (clientWithActiveAccess.size() > 0) return null;
        }

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

    public void addClient(Access access, Client client){
        if(! access.getClientCollection().contains(client)){
            access.addClient(client);
            accessRepository.update(access);
        }
    }

    public void addEquipment(Access access, Equipment equipment){
        if(! access.getEquipmentCollection().contains(equipment)){
            access.addEquipment(equipment);
            accessRepository.update(access);
        }
    }

    public boolean payAndCloseAccess(Access access, Payment payment){
        if(payment.getAmount() < 0) return false;
        //if(paymentRepository.findById(payment.getId()) != null) return false;
        if(access.getPayment() != null) return false;

        access.setPayment(payment);
        access.setExitDate(new Timestamp(System.currentTimeMillis()));
        accessRepository.update(access);

        return true;
    }

    public List<Equipment> findEquipmentInUse(){
        return accessRepository.findEquipmentInUse();
    }

    public List<Client> findClientWithActiveAccess(){
        return accessRepository.findClientWithActiveAccess();
    }


}
