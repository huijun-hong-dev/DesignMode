package com.designmode.examples.StragetyPattern;

/**
 * CardFeeStrategy
 */
public class CardFeeStrategy implements FeeStrategy {
    @Override
    public double calculateFee(double amount) {
        // 银行卡手续费为0.1%
        return amount * 0.001;
    }
}
