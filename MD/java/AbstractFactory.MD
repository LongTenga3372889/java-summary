# 抽象工厂模式

## 成员

抽象工厂(AbstractFactory):申明一组用于创建一族产品的方法，每个方法对应一种产品。

具体工厂(ConcreteFactory):实现抽象工厂的方法，生成一组具体的产品。

抽象产品(AbstractFactory):他为每种产品申明接口。

具体产品(ConcreteFactory):实现抽象产品的接口方法。

## 代码

抽象产品
```java
public abstract class Message{
    void getMessage();
}

public abstract class Video{
    void getVideo();
}
```

具体产品
```java
public class QQMessage extends Message{
    @Override
    public void getMessage(){
        System.out.println("我是QQ的发送消息");
    }
}

public class DDMessage extends Message{
    @Override
    public void getMessage(){ 
        System.out.println("我是钉钉的发送消息"); 
    }
}

public class QQVideo extends Video{
    @Override
    public void getVideo(){
        System.out.println("我是QQ的音乐");
    }
}

public class DDVideo extends Video{
    @Override
    public void getVideo(){
        System.out.println("我是钉钉的音乐");
    }
}
```

抽象工厂
```java
public interface Factory{
    Message getMessage();
    
    Video getVideo();
}
```

具体工厂
```java
public class QQFactory implements Factory{
    public Message getMessage(){
        return new QQMessgae();
    }
    
    public Video getVideo(){
        return new QQVideo();
    }
}

public class DDFactory implements Factory{
    public Message getMessage(){
        return new DDMessage();
    }
    
    public Video getVideo(){
        return new DDVideo();
    }
}
```

测试
```java
public class Test{
    public static void main(String[] args){
       Message message;
       Video video;
       Factory factory = Class.forName("xxx.xxx.xxx.QQFactory");
       message = factory.getMessage();
       video = factory.getVideo();
       message.getMessage();
       video.getVideo();
    }
}
```

## 优缺点

### 优点

(1)新增产品族很简单不需要修改已有代码，符合开闭原则

(2)抽象工厂模式隔离了具体类的生成。

### 缺点

(1)增加产品等级结构会很烦吗，需要修改已有代码。
