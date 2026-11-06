package com.designmode.examples.DecoratorPattern.CoffeeDemo;

public class SugarDecorator extends CoffeeDecorator {
     
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 2.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "加糖";
    }

}

