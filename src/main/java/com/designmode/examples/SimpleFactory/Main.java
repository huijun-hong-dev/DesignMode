package com.designmode.examples.SimpleFactory;

public class Main {
    public static void main(String[] args) {
        OrderService order = new OrderService();
        order.checkout("alipay", 99.9);
        order.checkout("wechat", 50.0);
    }
}