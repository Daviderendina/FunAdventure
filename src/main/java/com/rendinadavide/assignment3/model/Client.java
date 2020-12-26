package com.rendinadavide.assignment3.model;

import com.rendinadavide.assignment3.services.IdGenerator;

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

    // TODO Se client minorenne non può non avere accompagnatore
    public Client(String name, String surname, Date bDate) {
        this.id = IdGenerator.getIstance().getUID();
        this.name = name;
        this.surname = surname;
        this.bDate = bDate;
        this.companionSet = new HashSet<>();
    }

    public Client(String name, String surname, Date bDate, Client companion) {
        this(name, surname, bDate);
        //TODO controllo che companion è adulto?
        this.companionSet.add(companion);
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


    public void addCompanion(Client client){
        //TODO aggiungi solo se è maggiorenne
        this.companionSet.add(client);
    }
}
