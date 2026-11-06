package com.designmode.examples.DIP;

public class AliPay implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("支付宝: " + amount);
    }
}