package com.designmode.examples.SimpleFactory.models;

public class CardPay implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("银行卡支付: " + amount + "元");
    }
}
