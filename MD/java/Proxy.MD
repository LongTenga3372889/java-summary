# 静态代理

静态代理是在jvm编译的时候把所有的接口、实现类全部手动完成，而且会有大量的重复代码。
优点在于扩展原有功能，不会侵入原有代码。

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