package com.designmode.examples.SimpleFactory;

import com.designmode.examples.SimpleFactory.models.Payment;

public class WechatPay implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("微信支付: " + amount + "元");
    }
}
