package com.rendinadavide.assignment3.model;

import java.util.Date;

public class Client {

    private int id;
    private String name;
    private String surname;
    private Date bDate;

    private Client companion;

    // TODO accompagnatore deve essere maggiorenne e se client minorenne non può non avere accompagnatore
    public Client(String name, String surname, Date bDate) {
        this.id = 1; //TODO
        this.name = name;
        this.surname = surname;
        this.bDate = bDate;
    }

    public Client(String name, String surname, Date bDate, Client companion) {
        this(name, surname, bDate);
        this.companion = companion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Client getCompanion() {
        return companion;
    }

    public void setCompanion(Client companion) {
        this.companion = companion;
    }
}
