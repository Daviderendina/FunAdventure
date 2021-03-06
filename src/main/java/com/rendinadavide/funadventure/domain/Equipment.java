package com.rendinadavide.funadventure.domain;

import com.rendinadavide.funadventure.utils.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Equipment {

    @Id
    private String id;
    private LocalDate purchaseDate;
    private String serialNumber;

    public Equipment(LocalDate purchaseDate, String serialNumber) {
        this.id = IdGenerator.getIstance().getUID();
        this.purchaseDate = purchaseDate;
        this.serialNumber = serialNumber;
    }

    public Equipment(){};

    public String getId() {
        return id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(id, equipment.id) &&
                Objects.equals(purchaseDate, equipment.purchaseDate) &&
                Objects.equals(serialNumber, equipment.serialNumber);
    }
}
