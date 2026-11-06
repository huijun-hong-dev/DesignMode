# 单一职责原则 single responsibility principle
> **There should never be more than one reason for a class to change.**
>
> 一个类，应该有且仅有一个引起它变化的原因。
```java
// ❌ 一个类干了好几件事
public class User {
private String name;
private String email;
private String password;
// 职责1：用户本身的属性（getter/setter）
public String getName() { return name; }
public void setName(String name) { this.name = name; }

// 职责2：用户数据的持久化
public void saveToDatabase() {
    System.out.println("保存到数据库");
}

// 职责3：用户数据校验
public boolean validateEmail() {
    return email != null && email.contains("@");
}

public boolean validatePassword() {
    return password != null && password.length() >= 8;
}

// 职责4：用户报表导出
public void exportToExcel() {
    System.out.println("导出用户数据到 Excel");
}
}
```
这个类干了四件事：
1. 用户属性（它该干的）
2. 数据库操作（该 DAO 干的）
3. 校验逻辑（该 Validator 干的）
4. 报表导出（该 Exporter 干的）

---

## 二、坏处在哪

| 变化原因 | 改哪个类 | 问题 |
|---------|---------|------|
| 数据库从 MySQL 换 PostgreSQL | `User` | 一个用户类为啥要改？ |
| 校验规则变了（邮箱要验证域名白名单） | `User` | 用户属性没变，类却要改 |
| 报表格式从 Excel 换成 PDF | `User` | 跟用户有啥关系？ |
| 加个新字段 `phone` | `User` | OK，这个改得合理 |

**四个不同的变化原因，全打在一个类上。** 改任何一个都可能把别的搞挂。

---

## 三、按 SRP 拆开

### 3.1 只管用户属性
```java
public class User {
private String name;
private String email;
private String password;
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }
}
```

### 3.2 只管持久化
```java
public class UserRepository {
public void save(User user) {
System.out.println("保存用户: " + user.getName());
}
public User findById(Long id) {
    return null;
}
}
```

### 3.3 只管校验
```java
public class UserValidator {
public boolean validate(User user) {
return user.getEmail() != null && user.getEmail().contains("@")
&& user.getPassword() != null && user.getPassword().length() >= 8;
}
}
```
### 3.4 只管导出
```java
public class UserExporter {
public void exportToExcel(User user) {
System.out.println("导出到 Excel: " + user.getName());
}
}
```


### 3.5 客户端编排
```java
public class UserService {
private final UserRepository repository;
private final UserValidator validator;
private final UserExporter exporter;
public UserService(UserRepository repository, UserValidator validator, UserExporter exporter) {
    this.repository = repository;
    this.validator = validator;
    this.exporter = exporter;
}

public void register(User user) {
    if (!validator.validate(user)) {
        throw new IllegalArgumentException("用户数据不合法");
    }
    repository.save(user);
}

public void export(User user) {
    exporter.exportToExcel(user);
}
}
```
---

## 四、拆前 vs 拆后

| | 拆之前 | 拆之后 |
|--|--------|--------|
| 改数据库逻辑影响用户属性吗 | 会（同一个类） | **不会**（User 完全不动） |
| 加校验规则改几个文件 | 1个（User） | **1个**（UserValidator） |
| 类有多长 | 100+ 行 | 每个 20~40 行 |
| 能单独测试校验逻辑吗 | 难 | **容易**（new UserValidator 直接测） |
| 代码可读性 | 翻半天找逻辑 | 一眼知道去哪个类 |

---

## 五、怎么判断一个类违反了 SRP

问自己一个问题：**"这个类会因为什么而改？"** 如果答案超过一个，就拆。

| 类名 | 变化原因 | 几个 | 判断 |
|------|---------|------|------|
| `User` | 用户属性变了 | 1 | ✅ |
| `UserManager` | 属性变、存库变、校验变、导出变 | 4 | ❌ |
| `OrderService` | 下单流程变、支付方式变、手续费算法变 | 3 | ❌ |
| `PaymentFactory` | 加新支付方式 | 1 | ✅ |

> 带 `Manager`、`Processor`、`Handler`、`Service` 这种名字的类，最容易违反 SRP——因为啥都往里塞。

---

## 六、容易踩的坑

### ❌ 拆太碎（过度设计）
### ✅ 正确的粒度

- 一个类里的方法都在同一层面上做同一件事 → OK
- 一个类里的方法涉及不同关注点（存数据、算业务、打日志、发消息）→ 该拆

---
## 七、跟已学模式的关系

| 模式 | 跟 SRP 的关系 |
|------|-------------|
| 简单工厂 | 把"创建对象"从业务类里拽出来 → **拆了两个变化原因** |
| 策略模式 | 把"算法/行为"从上下文里拽出来 → **拆了两个变化原因** |
| BadOrderService | 下单 + 创建搅一起 → **违反 SRP** |

> 设计模式本质上就是在帮你遵守 SOLID 原则。模式是招式，原则是心法。

---

## 八、现实类比

> 你去找一个人，他既是厨师又是收银员又是清洁工又是采购。今天采购流程变了，他得重新培训，结果可能把做菜也搞砸。拆成四个人，各管各的，谁变改谁，互不干扰。

---

## 九、一句话总结

> **一个类只干一件事 = 只有一个理由让它改。**