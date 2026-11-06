package com.designmode.examples.StragetyPattern;

// ===== Client =====
public class Main {
    public static void main(String[] args) {
        OrderService order = new OrderService();

        // use Alipay strategy
        order.setStrategy(new AlipayFeeStrategy());
        System.out.println("支付宝手续费: " + order.checkout(1000));

        // use Card strategy
        order.setStrategy(new CardFeeStrategy());
        System.out.println("银行卡手续费: " + order.checkout(1000));
    }
}
