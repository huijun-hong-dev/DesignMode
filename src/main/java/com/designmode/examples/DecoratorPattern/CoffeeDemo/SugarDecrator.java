package com.designmode.examples.DecoratorPattern.CoffeeDemo;

public class SugarDecrator extends CoffeeDecorator {
     
    public SugarDecrator(Coffe coffe) {
        super(coffe);
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

