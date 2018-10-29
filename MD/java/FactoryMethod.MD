# 工厂方法模式

## 成员

抽象工厂(Factory):是所有工厂的父类，用于返回产品对象。抽象工厂是工厂方法的核心。

具体工厂(ConcreteFactory):是抽象工厂的子类。实现抽象工厂的方法。

抽象产品(product):是所有的实现产品的父类。一般以接口的方式。封装了各种产品对象的公有方法。

具体产品(ConcreteProduct):实现类。需要实现抽象产品中的方法。

## 代码

抽象产品
```java
public interface Food{
    void getName();
}
```

具体产品
```java
public class Apply implements Food{
    @Override
    public void getName(){
        System.out.println("我是苹果");
    }
}

public class Orange implements Food{
    @Override
    public void getName(){
        System.out.println("我是橘子");
    }
}
```

抽象工厂
```java
public interface FoodFactory{
    Food getFood();
}
```

具体工厂
```java
public class ApplyFactory implements FoodFactory{
    @Override
    public Food getFood(){
        return new Apply();
    }
}

public class OrangeFactory implements FoodFactory{
    @Override
    public Food getFood(){
        return new Orange();
    }
}
```

测试案例
```java
public class Test{
    public static void main(String[] args){
      FoodFactory foodFactory = Class.forName("xxx.xxx.xxx.ApplyFactory");
      Food food = foodFactory.getFood();
      food.getName();
    }
}
```
这里可以看到我们想增加其他的食物完全不需要修改之前的代码。我们只需要增加一个具体食物，和具体食物的工厂即可。

我们还可以在具体工厂中隐藏具体食物，这样客户端无需通过工厂创建对象，我们可以直接封装到具体工厂中。

## 优缺点

### 优点

(1)自然就是增加一个新成员不需要修改任何代码，只需要增加一个成员的对象，和成员的工厂对象即可。

(2)基于工厂角色和产品角色试程序呈现为多态方式。

(3)用工厂方法模式，可以向客户端隐藏所需要的产品。甚至可以隐藏产品，全部在工厂上执行。

### 缺点

(1)自然而然需要些其他类，增加了代码量和编译与运行的成本。

(2)使程序更为抽象增加了系统的抽象性和理解难度。