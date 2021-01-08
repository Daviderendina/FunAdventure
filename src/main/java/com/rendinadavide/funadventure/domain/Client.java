package com.rendinadavide.funadventure.domain;

import com.rendinadavide.funadventure.utils.IdGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Entity
public class Client {

    @Id
    private String id;
    private String name;
    private String surname;
    private LocalDate bDate;

    @OneToMany
    @JoinTable(name = "CompanionClient",
            joinColumns = @JoinColumn(name = "ClientID"),
            inverseJoinColumns = @JoinColumn(name = "CompanionID"))
    private Set<Client> companionSet;

    public Client(){}

    public Client(String name, String surname, LocalDate bDate) {
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

    public LocalDate getbDate() {
        return bDate;
    }

    public void setbDate(LocalDate bDate) {
        this.bDate = bDate;
    }

    public Set<Client> getCompanionSet() {
        return companionSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        boolean equals = Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(bDate, client.bDate) &&
                companionSet.size() == client.companionSet.size();

        if(equals) {
            for (int i = 0; i < companionSet.size(); i++){
                Client c1 = companionSet.iterator().next();
                Client c2 = client.companionSet.iterator().next();
                if(! c1.equals(c2))
                    equals = false;
            }
            //Objects.equals(companionSet, client.companionSet);
        }

        return equals;
    }

    public int calculateAge(){
        return Period.between(bDate, LocalDate.now()).getYears();
    }

    public void addCompanion(Client client){
        this.companionSet.add(client);
    }
}
