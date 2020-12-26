package com.rendinadavide.assignment3.model.payment;

import com.rendinadavide.assignment3.services.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@MappedSuperclass
public abstract class Payment {

    private String id;
    private float amount;
    private Date date;

    public Payment(float amount) {
        this.id = IdGenerator.getIstance().getUID();
        this.amount = amount;
        this.date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
