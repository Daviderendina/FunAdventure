package com.rendinadavide.funadventure.domain;

import com.rendinadavide.funadventure.utils.IdGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Client {

    @Id
    private String id;
    private String name;
    private String surname;
    private Date bDate;

    @OneToMany
    @JoinTable(name = "CompanionClient",
            joinColumns = @JoinColumn(name = "ClientID"),
            inverseJoinColumns = @JoinColumn(name = "CompanionID"))
    private Set<Client> companionSet;

    public Client(){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(bDate, client.bDate) &&
                Objects.equals(companionSet, client.companionSet);
    }

    public int calculateAge(){
        LocalDate bDay = LocalDate.ofInstant(bDate.toInstant(), ZoneId.systemDefault());
        return Period.between(bDay, LocalDate.now()).getYears();
    }

    public void addCompanion(Client client){
        this.companionSet.add(client);
    }
}
