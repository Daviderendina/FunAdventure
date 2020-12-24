package com.rendinadavide.assignment3.model.payment;


public class VoucherPayment extends Payment {

    private String serialNumber;

    public VoucherPayment(float amount, String serialNumber) {
        super(amount);
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
