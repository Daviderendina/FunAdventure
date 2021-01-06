package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.domain.Access;
import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.domain.Equipment;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ServiceFacade {

    EquipmentService equipmentService;
    ClientService clientService;
    AccessService accessService;

    public ServiceFacade(){
        equipmentService = new EquipmentService();
        clientService = new ClientService();
        accessService = new AccessService();
    }


    public Equipment createEquipment(Date purchaseDate, String sn){
        return equipmentService.create(purchaseDate, sn);
    }

    public Client createClient(String name, String surname, Date bDate){
        return clientService.create(name, surname, bDate);
    }

    public Access createAccess(Set<Client> clientSet, Set<Equipment> equipmentSet){
        return accessService.create(clientSet, equipmentSet);
    }


    public Client findClientById(String Id){
        return clientService.findById(Id);
    }

    public Access findAccessById(String Id){
        return accessService.findById(Id);
    }

    public Equipment findEquipmentById(String Id){
        return equipmentService.findById(Id);
    }


    public List<Client> findAllClient(){
        return clientService.findAll();
    }

    public List<Access> findAllAccess(){
        return accessService.findAll();
    }

    public List<Equipment> findAllEquipment(){
        return equipmentService.findAll();
    }

    //update

    public void deleteClient(Client client){
        clientService.delete(client);
    }

    public void deleteEquipment(Equipment equipment){
        equipmentService.delete(equipment);
    }

    public void deleteAccess(Access access){
        accessService.delete(access);
    }


}
