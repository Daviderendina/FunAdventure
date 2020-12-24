package com.rendinadavide.assignment3.model;

import com.rendinadavide.assignment3.services.IdGenerator;

import java.util.Date;

public class Equipment {

    private String id;
    private Date purchaseDate;
    private String serialNumber;

    public Equipment(Date purchaseDate, String serialNumber) {
        this.id = IdGenerator.getIstance().getUID();
        this.purchaseDate = purchaseDate;
        this.serialNumber = serialNumber;
    }

    public String getId() {
        return id;
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
