package com.designmode.examples.DecoratorPattern.CoffeeDemo;

// 具体装饰者：摩卡
public class MochaDecorator extends CoffeeDecorator {

    public MochaDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 8.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " 加摩卡";
    }

}
