package com.rendinadavide.assignment3.model.payment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public abstract class Payment {

    private int id;
    private float amount;
    private Date date;

    public Payment(float amount) {
        this.id = 1; //TODO
        this.amount = amount;
        this.date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
