package com.designmode.examples.DecoratorPattern.CoffeeDemo;

public class MilkDecrator extends CoffeeDecorator {

    public MilkDecrator(Coffe coffe) {
        super(coffe);
    }

    @Override
    public double cost() {
        return super.cost() + 5.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " 加牛奶";
    }

}
