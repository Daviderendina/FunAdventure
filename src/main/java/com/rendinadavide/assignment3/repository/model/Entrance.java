package com.rendinadavide.assignment3.repository.model;

import com.rendinadavide.assignment3.repository.model.payment.Payment;
import com.rendinadavide.assignment3.service.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Entrance {

    @Id
    private String id;
    private Date date;

    @ManyToMany
    private Set<Client> clientCollection;
    @ManyToMany
    private Set<Equipment> equipmentCollection;
    private Payment payment;

    public Entrance() {
        this.id = IdGenerator.getIstance().getUID();
        this.date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        this.clientCollection = new HashSet<>();
        this.equipmentCollection = new HashSet<>();
    }

    public String getId() {
        return id;
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
