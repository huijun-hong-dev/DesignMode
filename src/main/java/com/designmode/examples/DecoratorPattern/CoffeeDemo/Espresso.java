package com.designmode.examples.DecoratorPattern.CoffeeDemo;
// 具体组件
public class Espresso implements Coffee {

    @Override
    public double cost() {
        return 20.0;
    }

    @Override
    public String getDescription() {
        return "浓缩咖啡";
    }

}
