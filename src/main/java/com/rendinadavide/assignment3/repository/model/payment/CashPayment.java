package com.rendinadavide.assignment3.repository.model.payment;

import javax.persistence.Entity;

@Entity
public class CashPayment extends Payment{

    public CashPayment(float amount) {
        super(amount);
    }
}