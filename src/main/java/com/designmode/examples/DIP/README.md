# 依赖倒转原则（DIP — Dependency Inversion Principle）

> **High-level modules should not depend on low-level modules. Both should depend on abstractions.**
>
> **Abstractions should not depend on details. Details should depend on abstractions.**
>
> 高层模块不应该依赖低层模块，两者都应该依赖抽象。抽象不应该依赖细节，细节应该依赖抽象。

翻译过来就一句：**依赖接口，不依赖具体类。**

---

## 一、先看"正着来"有什么问题

// ❌ 高层直接依赖低层
```java
public class OrderService {              // 高层模块（业务逻辑）
    private Alipay alipay;               // 直接依赖具体类

    public OrderService() {
        this.alipay = new Alipay();      // 直接 new
    }

    public void checkout(double amount) {
        alipay.pay(amount);
    }
}
```

问题

| 变化 | 影响 |
|------|------|
| 加微信支付 | OrderService要改（加 WechatPay字段、改构造） |
| Alipay构造参数变了 | OrderService跟着改 |
| 想写单元测试 | 没法 mock，Alipay直接写死在里面 |
| 换支付渠道 | 换支付渠道 |

依赖方向：

OrderService（高层）──→ Alipay（低层）

高层依赖低层，低层一变高层就跟着变。这就是"正着来"的问题。

## 二、DIP 怎么倒转
```
OrderService（高层）──→ Payment（接口）←── Alipay（低层）
                         ↑
                    WechatPay（低层）

```                    

高层和低层都依赖同一个接口。谁也不直接依赖谁。

## 三、DIP 的三句话拆解

| 原文 | 翻译 | 怎么做 |
|------|------|--------|
| 高层不依赖低层 | 业务逻辑不依赖具体实现 | 两者都依赖接口 |
| 两者都依赖抽象 | 接口是中间人 | 定义接口，高层和低层都实现/引用它 |
| 抽象不依赖细节 | 接口不能因为实现变了而改 | 接口稳定，实现随便换 |

---

## 四、DIP 跟已学模式/原则的关系

### 4.1 DIP 是 SRP + OCP 的"黏合剂"

| 原则 | 做了什么 | DIP 怎么帮它 |
|------|---------|-------------|
| SRP | 拆出不同职责 | DIP 让拆出来的模块通过接口通信，而不是互相直接引用 |
| OCP | 加功能不改老代码 | DIP 让高层代码只认接口，新实现随便加，高层一行不动 |
| 简单工厂 | 集中创建 | 工厂返回接口类型，客户端拿接口 |
| 策略模式 | 行为可替换 | 上下文持有策略接口 → 这就是 DIP 的体现 |

## 五、跟 SOLID 全景的关系

| 原则 | 一句话 | DIP 的角色 |
|------|--------|-----------|
| SRP | 一个类只干一件事 | 拆出独立模块 |
| OCP | 加功能不改老代码 | 通过接口扩展 |
| **DIP** | **依赖接口不依赖实现** | **让 SRP 拆出来的模块、OCP 扩展出来的实现，能松耦合地协作** |
| LSP | 子类能替换父类 | DIP 的多态能成立的前提 |
| ISP | 接口要小而专 | 让 DIP 依赖的接口刚好够用，不强迫实现不需要的方法 |

> **DIP 是 SOLID 的"架构级原则"——它决定了模块之间怎么连。**

---
## 六、一句话总结

> **DIP = 面向接口编程。高层不 new 低层，两者都认同一个接口。谁也不直接依赖谁，换实现像换插头一样简单。**

---
