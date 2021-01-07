package com.rendinadavide.funadventure.domain.payment;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class CardPayment extends Payment {

    private String transactionNumber;

    public CardPayment(float amount, String transactionNumber) {
        super(amount);
        this.transactionNumber = transactionNumber;
    }
    public CardPayment(){}

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CardPayment that = (CardPayment) o;
        return Objects.equals(transactionNumber, that.transactionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), transactionNumber);
    }
}
