# 简单工厂

## 成员

工厂(Factory):用于创建具体产品的实例对象。也是工厂类的核心。

抽象产品(product):是所有的实现产品的父类。一般以接口的方式。封装了各种产品对象的公有方法。

具体产品(ConcreteProduct):实现类。需要实现抽象产品中的方法。

## 代码

(1)产品类

```java
public interface Product{
    void getName();
}
```

(2)具体产品类
```java
public class ConcreteProductA extends  Product{
    @Override
    public void getName(){
        System.out.println("我是:ConcreteProductA");
    }
}

public class ConcreteProductB extends Product{
    @Override
    public void getName(){
        System.out.println("我是:ConcreteProductB");
    }
}
```

(3)工厂类
```java
public class Factory{
    public Product getProduct(String name){
        switch (name){
            case "A":
                return new ConcreteProductA();
            case "B":
                return new ConcreteProductB();
            default: return null;
        }
    }
}
```

## 优缺点

### 优点

(1)工厂类包含必要的逻辑，什么时候创建什么样的实现类。使客户端避免直接创建对象。

(2)客户端无需知道所创建对象的类名，只需要知道具体类所对应的参数就可以了。

(3)可以通过引入配置或者数据库，使增加产品后不需要修改任何客户端代码。

### 缺点

(1)工厂类集中所有产品的逻辑，当工厂类中断时，整个系统也就中断了。

(2)类的个数增加，增加系统的复杂度。

(3)维护困难，一旦增加了实例就必须修改工厂类。