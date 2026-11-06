package com.designmode.examples.StragetyPattern;

/**
 * AlipayFeeStrategy
 */
public class AlipayFeeStrategy implements FeeStrategy {

    @Override
    public double calculateFee(double amount) {
       return amount * 0.006; // 支付宝手续费为0.6%
    }

}
