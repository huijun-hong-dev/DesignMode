package com.designmode.examples.DIP;

public class OrderService {
    private Payment payment; // depends on abstract

    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void checkout(double amount) {
        payment.pay(amount);
    }

}
