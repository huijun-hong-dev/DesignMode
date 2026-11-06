package com.designmode.examples.DecoratorPattern.CoffeeDemo;

// 抽象装饰者
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;  // 持有被装饰者（或内层装饰者）

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override 
    public double cost() {
        return coffee.cost();
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

}
