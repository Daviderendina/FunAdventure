package com.rendinadavide.assignment3.repository.model;

import com.rendinadavide.assignment3.service.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Equipment {

    @Id
    private String id;
    private Date purchaseDate;
    private String serialNumber;

    public Equipment(Date purchaseDate, String serialNumber) {
        this.id = IdGenerator.getIstance().getUID();
        this.purchaseDate = purchaseDate;
        this.serialNumber = serialNumber;
    }

    public Equipment(){};

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
