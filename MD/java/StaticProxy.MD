# 静态代理

优点：
（1）在于代理使客户端不需要知道实现类是什么，怎么做的，而客户端只需知道代理即可（解耦合）。

（2）扩展原有功能，不会侵入原有代码。

缺点：
（1）代理类和委托类实现了相同的接口，代理类通过委托类实现了相同的方法。这样就出现了大量的代码重复。如果接口增加一个方法，除了所有实现类需要实现这个方法外，所有代理类也需要实现此方法。增加了代码维护的复杂度

（2）代理对象只服务于一种类型的对象，如果要服务多类型的对象。势必要为每一种对象都进行代理，静态代理在程序规模稍大时就无法胜任了。下面的代码是只为BookService类的访问提供了代理，但是如果还要为其他类如PhoneService类提供代理的话，就需要我们再次添加代理PhoneService的代理类。

## 静态代理的程序设计

(1)定义接口

```java
public interface IBookService{
    void getBookName(String s);
}
```

(2)创建实例对象
```java
public class BookService implements IBookService{
    @Override
    public void getBookName(String s){
        System.out.println("我叫:"+s);
    }
}
```

(3)创建代理对象
```java
public class Proxy implements IBookService{
    private IBookService iBookService;
    
    public Proxy(String iBookService){
        this.iBookService = iBookService;
    }
    
    public void getBookName(String s){
        iBookService.getBookName(s);
    }
}
```

(4)运行代码
```java
public static void main(String[] args){
  Proxy proxy = new Proxy(new BookService());
  proxy.getBookName("JAVA编程的逻辑");
}
```