package com.rendinadavide.funadventure.service;

import com.rendinadavide.funadventure.domain.payment.*;

public class PaymentFactory {

    public enum PaymentType{CASH, CARD, VOUCHER}

    public static Payment getPayment(float amount, String str, PaymentType type){
        if(amount < 0) return null;

        switch (type){
            case CASH:
                return new CashPayment(amount);
            case CARD:
                if(checkValidStr(str)) return new CardPayment(amount, str);
            case VOUCHER:
                if(checkValidStr(str)) return new VoucherPayment(amount, str);
        }
        return null;
    }

    private static boolean checkValidStr(String str){
        return str != null && ! str.isEmpty();
    }
}
