package com.rendinadavide.funadventure.domain.payment;

import javax.persistence.Entity;

@Entity
public class CashPayment extends Payment{

    public CashPayment(float amount) {
        super(amount);
    }

    public CashPayment(){}

}
