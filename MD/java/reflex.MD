# JAVA反射

反射是框架设计的灵魂
 
使用的前提条件：必须先得到代表的字节码的Class，Class类用于表示.class文件（字节码）
 
## 什么叫反射

  JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取信息以及动态调用对象的方法的功能称为java语言的反射机制。

  要想解剖一个类,必须先要获取到该类的字节码文件对象。而解剖使用的就是Class类中的方法.所以先要获取到每一个字节码文件对应的Class类型的对象
  
  反射就是把java类中的各种成分映射成一个个的Java对象。例如：一个类有：成员变量、方法、构造方法、包等等信息，利用反射技术可以对一个类进行解剖，把个个组成部分映射成一个个对象。

## Class

  所有的反射都需要得到Class对象。
  
  获取Class对象的方式
  
  （1）Object ——> getClass();
  
  （2）任何数据类型（包括基本数据类型）都有一个“静态”的class属性
  
  （3）通过Class类的静态方法：forName（String  className）(常用)
  
```java
public class Test{
    public static void main(String[] args){
      //第一种方式  
      java.util.Date date = new java.util.Date();
      Class clazz = date.getClass();
      System.out.println(clazz.getName());
      
      //第二种方式
      Class clazz2 = java.util.Date.class;
      //判断地址时候一样
      System.out.println(clazz2==clazz);
      
      //第三种模式
      Class clazz3 = Class.forName("java.util.Date");
      System.out.println(clazz3==clazz2);
    }
}
```

注意：在运行期间一个类有且仅有一个Class对象。

## 通过反射获取构造方法

```java
public class Test2{
    public static void main(String[] args){
        Class aClass = java.util.Date.class;
        System.out.println("所有公有构造方法");
        Constructor[] constructors = aClass.getConstructors();
        java.util.Arrays.stream(constructors).forEach(constructor->System.out.println(constructor));
        
        System.out.println("所有的私有、受保护、默认、公有方法");
        Constructor[] constructors1 = aClass.getDeclaredConstructors();
        java.util.Arrays.stream(constructors1).forEach(constructors1->System.out.println(constructors1));
        
        //注意因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //返回的是描述这个无参构造函数的类对象。
        System.out.println("得到公有的无参数方法");
        Constructor c = aClass.getConstructor(null);
        System.out.println(c);
        
        
    }
}
```

（1）批量得到构造方法

public Constructor[] getConstructors()：所有"公有的"构造方法。

public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)。

（2）单个获取构造方法

public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法。

public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有。

（3）调用构造方法

Constructor-->newInstance(Object... initargs)。

## 通过反射获取对象

```java
public class Test3{
    public static void main(String[] args){
      Class clazz = java.util.Date.class;
      //获取所有的公有字段
      Field[] fields = clazz.getFields();
      java.util.Arrays.stream(fields).forEach(field->System.out.println(field));
      
      //获取所有公有私有受保护的字段
      Field[] fields1 = clazz.getDeclaredFields();
      java.util.Arrays.stream(fields1).forEach(field->System.out.println(field));
      
      //获取单个字段并调用
      Field field = clazz.getDeclaredField("fastTime");
      //相当于new一个clazz对象
      Object obj = clazz.getConstructor(null).newInstance();
      //暴力解除私有限定
      field.setAccessible(true);
      field.set(obj,10000);
      java.util.Date date = (Date) obj;
      System.out.println(date);
    }
}
```

## 通过反射获取方法并执行
```java
public class Test4{
    public static void main(String[] args){
      Class clazz = java.util.Date.class;
      //获取所有公有方法
      Method[] methods = clazz.getMethods();
      java.util.Arrays.stream(methods).forEach(System.out::println);
      //获取所有公有，私有，受保护的方法
      Method[] methods1 = clazz.getDeclaredMethods();
      java.util.Arrays.stream(methods1).forEach(System.out::println);
      //调用
      Method method = clazz.getDeclaredMethod("setDate",int.class);
      method.setAccessible(true);
      Object o = clazz.getConstructor(null).newInstance();
      method.invoke(o,15);
      System.out.println(o);
    }
}
```