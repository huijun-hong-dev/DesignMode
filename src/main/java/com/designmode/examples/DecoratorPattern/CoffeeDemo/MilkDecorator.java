package com.designmode.examples.DecoratorPattern.CoffeeDemo;

public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
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
