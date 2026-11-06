package com.designmode.examples.DecoratorPattern.CoffeeDemo;

// 抽象组件
public interface Coffee {
    // 获取价格
    double cost();

    // 获取描述
    String getDescription();

}
