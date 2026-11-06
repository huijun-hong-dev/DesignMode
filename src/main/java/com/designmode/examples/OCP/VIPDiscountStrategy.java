package com.designmode.examples.OCP;

import com.designmode.examples.StragetyPattern.FeeStrategy;

// OCP: add new discount strategy without modifying existing code
public class VIPDiscountStrategy implements FeeStrategy {

    @Override
    public double calculateFee(double amount) {
        return amount * 0.8; // VIP用户享受8折优惠
    }

}
