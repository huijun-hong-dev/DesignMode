package com.designmode.examples.DIP;

public class WechatPay implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("微信: " + amount);
    }

}
