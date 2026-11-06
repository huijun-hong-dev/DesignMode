# 简单工厂模式（Simple Factory）

## 一、问题场景

```java
public class OrderService {
public void checkout(String payType, double amount) {
Payment payment;
if ("alipay".equals(payType)) {
payment = new Alipay();
} else if ("wechat".equals(payType)) {
payment = new WechatPay();
} else if ("card".equals(payType)) {
payment = new CardPay();
} else {
throw new IllegalArgumentException("不支持的支付方式");
}
payment.pay(amount);
}
}
```

## 二、坏处

### 2.1 最直观的问题

**1. 客户端被迫知道所有具体类**

`OrderService` 里直接 `new Alipay()`、`new WechatPay()`，意味着它必须 `import` 这些具体类。调用方跟具体实现**紧耦合**——哪天 `Alipay` 改个构造参数，所有 `new Alipay()` 的地方全得改。

**2. `if-else` 散弹式修改**

如果退款、查账单、对账、统计也都要根据 `payType` 选支付方式，那同样的 `if-else` 要写 N 遍。加一个 Apple Pay，你得翻遍整个项目找所有写了这个 `if-else` 的地方改一遍——**漏改一个就是 bug**。

### 2.2 设计层面的问题

**3. 违反单一职责原则（SRP）**

`OrderService` 本来该干嘛？管下单流程。结果它还要管"怎么根据类型创建支付对象"。两个完全不同的职责搅在一起：

| 职责 | 该属于谁 |
|------|---------|
| 下单流程编排 | `OrderService` ✅ |
| 选择并创建支付方式 | 工厂 ✅（但它抢了） |

一个类管两件事 → 改其中一个理由就得动这个类 → 改出问题的概率翻倍。

**4. 违反开闭原则（OCP）**

加新支付方式 = 改 `OrderService` 的代码。每加一个就得改一次老文件。理想状态是：**加新功能只加新文件，不改老文件**。现在做不到。

**5. 没法单独测试支付逻辑**

你想写个单元测试只测 `Alipay.pay()` 对不对？不好意思，`Alipay` 被 `new` 死在 `OrderService` 里了，你没法在测试里换成 mock 或者 stub。

### 2.3 更深一层的问题

**6. 客户端和产品的生命周期绑定**

`OrderService` 里直接 `new`，意味着创建时机、创建方式、对象生命周期全由 `OrderService` 决定。如果哪天 `Alipay` 需要：
- 从连接池拿
- 带一个配置对象当参数
- 单例复用

那 `OrderService` 里所有 `new Alipay()` 全得改。工厂接管后这些变更**只发生在工厂内部**。

**7. 扩展方向被堵死**

假设后来需求变成：不同商户用不同支付渠道组合（商户 A 支持支付宝+微信，商户 B 只支持银行卡）。你这个 `if-else` 写死在 `OrderService` 里，逻辑一复杂，方法膨胀到几十行上百行，后面来的人不敢动、不想看、只能复制粘贴再加一个 `else if`。

> **简单工厂解决的核心就一个词：解耦。** 把"创建"这件事从 `OrderService` 里拽出来，它只管自己该管的下单流程，别的啥也不关心。

---

## 三、解决方案

把"创建对象"这件事集中到一个类里，客户端只说要什么，工厂负责怎么造。

`OrderService` 只需要引入：

| import | 为什么 |
|--------|--------|
| `Payment` | 声明 `Payment payment` 变量 |
| `PaymentFactory` | 调用 `PaymentFactory.create()` |

**不需要引入任何具体产品类**（`Alipay`、`WechatPay`、`CardPay`），这就是简单工厂带来的解耦效果。
