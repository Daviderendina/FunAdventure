package com.rendinadavide.assignment3.model;

import java.util.Date;

public class Equipment {

    private int id;
    private Date purchaseDate;
    private String serialNumber;

    public Equipment(Date purchaseDate, String serialNumber) {
        this.id = 1; //TODO
        this.purchaseDate = purchaseDate;
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
