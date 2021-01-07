package com.rendinadavide.funadventure.domain;

import com.rendinadavide.funadventure.domain.payment.Payment;
import com.rendinadavide.funadventure.utils.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Access {

    @Id
    private String id;
    private Timestamp entryDate;
    private Timestamp exitDate;

    @ManyToMany
    private List<Client> clientCollection;
    @ManyToMany
    private List<Equipment> equipmentCollection;
    @OneToOne
    private Payment payment;

    public Access() {
        this.id = IdGenerator.getIstance().getUID();
        this.entryDate = new Timestamp(System.currentTimeMillis());
        this.clientCollection = new ArrayList<>();
        this.equipmentCollection = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

    public List<Client> getClientCollection() {
        return clientCollection;
    }

    public List<Equipment> getEquipmentCollection() {
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

    public void setExitDate(Timestamp exitDate) {
        if(exitDate.compareTo(entryDate) > 0) this.exitDate = exitDate;
    }

    public Timestamp getExitDate() {
        return exitDate;
    }

    public void setClientCollection(List<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    public void setEquipmentCollection(List<Equipment> equipmentCollection) {
        this.equipmentCollection = equipmentCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Access access = (Access) o;
        return Objects.equals(id, access.id) &&
                Objects.equals(entryDate, access.entryDate) &&
                Objects.equals(exitDate, access.exitDate) &&
                clientCollection.containsAll(access.clientCollection) && clientCollection.size() == access.clientCollection.size() &&
                equipmentCollection.containsAll(access.equipmentCollection) && equipmentCollection.size() == access.equipmentCollection.size() &&
                Objects.equals(payment, access.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entryDate, exitDate, clientCollection, equipmentCollection, payment);
    }
}
