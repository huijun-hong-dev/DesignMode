package com.designmode.examples.DecoratorPattern.CoffeeDemo;

public class Americano implements Coffee {

    @Override
    public double cost() {
        return 15.0;
    }

    @Override
    public String getDescription() {
        return "美式咖啡";
    }

}
