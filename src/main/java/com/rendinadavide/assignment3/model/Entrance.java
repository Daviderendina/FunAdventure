package com.rendinadavide.assignment3.model;

import com.rendinadavide.assignment3.model.payment.Payment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Entrance {

    private int id;
    private Date date;

    private Set<Client> clientCollection;
    private Set<Equipment> equipmentCollection;
    private Payment payment;

    public Entrance() {
        //TODO id
        this.date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        this.clientCollection = new HashSet<>();
        this.equipmentCollection = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Client> getClientCollection() {
        return clientCollection;
    }

    public Set<Equipment> getEquipmentCollection() {
        return equipmentCollection;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void addClient(Client client){
        this.clientCollection.add(client);
    }

    public void addEquipment(Equipment equipment){
        this.equipmentCollection.add(equipment);
    }
}
