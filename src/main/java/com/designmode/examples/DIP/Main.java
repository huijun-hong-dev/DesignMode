package com.designmode.examples.DIP;


public class Main {
    public static void main(String[] args) {
        // 想换支付方式？只改这一行
        Payment payment = new AliPay();
        // Payment payment = new WechatPay();

        OrderService order = new OrderService(payment);
        order.checkout(100);
    }
}