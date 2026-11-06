package com.designmode.examples.SimpleFactory;

import com.designmode.examples.SimpleFactory.models.Payment;

public class OrderService {
    public void checkout(String payType, double amount) {
        Payment payment = PaymentFactory.create(payType);
        payment.pay(amount);
    }
}