# 装饰模式（Decorator Pattern）
>> ** Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.**
>>
> 动态地给一个对象添加额外的职责。装饰模式提供了比继承更灵活的扩展方式。

1. 先从一个"继承爆炸"的噩梦说起
咖啡店场景。基础咖啡 Espresso卖 20 块。可以加料：牛奶（+5）、糖（+2）、摩卡（+8）、奶油（+6）。
如果靠继承：
每种组合 = 一个新子类：

| 组合 | 类名 |
|------|------|
| 纯浓缩 | `Espresso` |
| 浓缩+牛奶 | `EspressoWithMilk` |
| 浓缩+糖 | `EspressoWithSugar` |
| 浓缩+摩卡 | `EspressoWithMocha` |
| 浓缩+奶油 | `EspressoWithWhip` |
| 浓缩+牛奶+糖 | `EspressoWithMilkAndSugar` |
| 浓缩+牛奶+摩卡 | `EspressoWithMilkAndMocha` |
| 浓缩+牛奶+奶油 | `EspressoWithMilkAndWhip` |
| 浓缩+糖+摩卡 | `EspressoWithSugarAndMocha` |
| 浓缩+糖+奶油 | `EspressoWithSugarAndWhip` |
| 浓缩+摩卡+奶油 | `EspressoWithMochaAndWhip` |
| 浓缩+牛奶+糖+摩卡 | `EspressoWithMilkAndSugarAndMocha` |
| 浓缩+牛奶+糖+奶油 | `EspressoWithMilkAndSugarAndWhip` |
| 浓缩+牛奶+摩卡+奶油 | `EspressoWithMilkAndMochaAndWhip` |
| 浓缩+糖+摩卡+奶油 | `EspressoWithSugarAndMochaAndWhip` |
| 浓缩+牛奶+糖+摩卡+奶油 | `EspressoWithEverything` |

每加一种加料，子类数量**指数级增长**。

### 加料顺序不同算不同价格 → 又翻倍
## 对比：装饰模式需要多少个类

| | 继承 | 装饰模式 |
|--|------|---------|
| 加料类 | 每种组合一个子类 | **每种加料一个装饰者类** |
| 4 种加料 | 15 个子类 | **4 个装饰者 + 1 个基础类 = 5 个** |
| 5 种加料 | 31 个子类 | **5 个装饰者 + 1 个基础类 = 6 个** |
| 6 种加料 | 63 个子类 | **7 个** |
| 组合方式 | 编译期定死 | **运行时随意嵌套** |
| 加新加料 | 新增 2ⁿ⁻¹ 个子类 | **加 1 个装饰者类** |

> 装饰模式把指数级增长压成了**线性增长**。

---

## 核心结论

> **继承是静态的、编译期就定死了，没法运行时随意组合。装饰模式把"组合"从编译期推迟到运行期，用线性数量的类覆盖指数级的组合可能。**

2. 结构
```
┌──────────────────┐
│  Component       │◄──── 抽象组件（被装饰者和装饰者都实现这个）
│  + cost()        │
│  + getDesc()     │
└────────┬─────────┘
         │
    ┌────┼────────────┐
    ▼    ▼            ▼
┌────────┐    ┌──────────────────┐
│Espresso│    │  Decorator       │◄──── 抽象装饰者
│+cost() │    │  - component     │     （持有 Component 引用）
│+getDesc│    └────────┬─────────┘
└────────┘             │
                  ┌────┼────┬────────┐
                  ▼    ▼    ▼        ▼
            ┌────────┐┌────────┐┌────────┐┌────────┐
            │Milk    ││Sugar   ││Mocha   ││Whip    │
            │Decorator││Decorator││Decorator││Decorator│
            │+cost() ││+cost() ││+cost() ││+cost() │
            │+getDesc││+getDesc││+getDesc││+getDesc│
            └────────┘└────────┘└────────┘└────────┘
```

##  装饰模式 vs 策略模式

| | 装饰模式 | 策略模式 |
|--|---------|---------|
| **目的** | 给对象加功能（增强） | 给对象换行为（替换） |
| **结构** | 层层包裹（递归嵌套） | 持有一个引用，直接替换 |
| **客户端感知** | 拿到的是"被包了 N 层的同一个接口" | 拿到的是"换了个实现的同一个接口" |
| **典型场景** | 加料、加日志、加缓存、加权限 | 换算法、换规则、换策略 |