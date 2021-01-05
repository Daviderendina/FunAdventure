package com.rendinadavide.funadventure.repository.payment;

import javax.persistence.Entity;

@Entity
public class CashPayment extends Payment{

    public CashPayment(float amount) {
        super(amount);
    }
}
