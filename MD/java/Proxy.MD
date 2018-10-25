# 静态代理

静态代理是在jvm编译的时候把所有的接口、实现类全部手动完成，而且会有大量的重复代码。
优点在于扩展原有功能，不会侵入原有代码。

## 静态代理的程序设计

(1)定义接口

```java
public interface IBook(){
    void getBookName();
}
```

