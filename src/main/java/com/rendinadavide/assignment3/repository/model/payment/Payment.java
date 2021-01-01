package com.rendinadavide.assignment3.repository.model.payment;

import com.rendinadavide.assignment3.service.IdGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment {

    @Id
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
