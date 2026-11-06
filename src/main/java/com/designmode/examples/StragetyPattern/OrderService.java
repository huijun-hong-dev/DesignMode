package com.designmode.examples.StragetyPattern;

/**
 * OrderService
 */
public class OrderService {

    private FeeStrategy feeStrategy;
       
    public void setStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public double checkout(double amount) {
        return feeStrategy.calculateFee(amount);
    }

}
