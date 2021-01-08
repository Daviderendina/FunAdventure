package com.rendinadavide.funadventure.domain.payment;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class VoucherPayment extends Payment {

    private String serialNumber;

    public VoucherPayment(float amount, String serialNumber) {
        super(amount);
        this.serialNumber = serialNumber;
    }

    public VoucherPayment(){}

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
        if (!super.equals(o)) return false;
        VoucherPayment that = (VoucherPayment) o;
        return Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), serialNumber);
    }
}
