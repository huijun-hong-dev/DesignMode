package com.designmode.examples.DecoratorPattern.CoffeeDemo;



public class Main {
    public static void main(String[] args) {
        // 纯浓缩
        Coffee coffee = new Espresso();
        System.out.println(coffee.getDescription() + " = ¥" + coffee.cost());

        // 浓缩 + 牛奶
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " = ¥" + coffee.cost());

        // 浓缩 + 牛奶 + 糖
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " = ¥" + coffee.cost());

           // 浓缩 + 牛奶 + 糖 + 摩卡（随意组合，顺序随便换）
        coffee = new MochaDecorator(coffee);
        System.out.println(coffee.getDescription() + " = ¥" + coffee.cost());

        // 换个基底也行
        Coffee americano = new SugarDecorator(new MilkDecorator(new Americano()));
        System.out.println(americano.getDescription() + " = ¥" + americano.cost());
    }

}
