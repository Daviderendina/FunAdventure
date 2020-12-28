package com.rendinadavide.assignment3.repository.model;

import com.rendinadavide.assignment3.service.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    private String id;
    private String name;
    private String surname;
    private Date bDate;

    @OneToMany
    @JoinTable(name = "CompanionClient",
            joinColumns = @JoinColumn(name = "ClientID"),
            inverseJoinColumns = @JoinColumn(name = "CompanionID"))
    private Set<Client> companionSet;

    public Client(String name, String surname, Date bDate) {
        this.id = IdGenerator.getIstance().getUID();
        this.name = name;
        this.surname = surname;
        this.bDate = bDate;
        this.companionSet = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public Set<Client> getCompanionSet() {
        return companionSet;
    }


    public boolean addCompanion(Client client){
        if(true /*TODO: client maggiorenne*/) {
            this.companionSet.add(client);
            return true;
        }
        return false;
    }
}
