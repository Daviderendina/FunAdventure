package com.rendinadavide.assignment3.model.payment;

public class CardPayment extends Payment {

    private String transactionNumber;

    public CardPayment(float amount, String transactionNumber) {
        super(amount);
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}