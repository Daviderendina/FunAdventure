package com.rendinadavide.funadventure.domain;

import com.rendinadavide.funadventure.domain.payment.Payment;
import com.rendinadavide.funadventure.utils.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Access {

    @Id
    private String id;
    private Date entryDate;
    private Date exitDate;

    @ManyToMany
    private Set<Client> clientCollection;
    @ManyToMany
    private Set<Equipment> equipmentCollection;
    @OneToOne
    private Payment payment;

    public Access() {
        this.id = IdGenerator.getIstance().getUID();
        this.entryDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        this.clientCollection = new HashSet<>();
        this.equipmentCollection = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
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

    public void setExitDate(Date exitDate) {
        if(exitDate.compareTo(entryDate) > 0) this.exitDate = exitDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setClientCollection(Set<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    public void setEquipmentCollection(Set<Equipment> equipmentCollection) {
        this.equipmentCollection = equipmentCollection;
    }
}
