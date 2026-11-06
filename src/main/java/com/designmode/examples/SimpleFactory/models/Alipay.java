package com.designmode.examples.SimpleFactory.models;

import com.designmode.examples.SimpleFactory.Payment;

public class Alipay implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("支付宝支付: " + amount + "元");
    }
}
