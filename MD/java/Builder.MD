# 建造者模式

## 成员

抽象建造者(Builder):他是所有建造者的核心，他可以是接口也可以是抽象类。

具体建造者(ConcreteBuilder):他是抽象建造者的实现类。

产品(Product):他是被构建的复杂对象，由具体建造者创建。

指挥者(Director):他负者复杂对象的建造顺序。

## 代码

(1)产品
```java
public class Car{
    
    private String steeringWheel;
    
    private String wiper;
    
    private String brand;
    
    public void getSteeringWheel(String steeringWheel){
        this.steeringWheel = steeringWheel;
    }
    public String setSteeringWheel(){
        return steeringWheel;
    }
    public void getWiper(String wiper){
        this.wiper = wiper;
    }
    public String setWiper(){
        return wiper;
    } 
    public void getBrand(String brand){
        this.brand = brand;
    }
    public String setBrand(){
        return brand;
    }     
}
```

(2)抽象建造者
```java
public abstract class Cars{
    Car car = new Car();
    
    public abstract void buildSteeringWheel();
    
    public abstract void buildWiper();
    
    public abstract void buildBrand();
    
    public Car crateCar(){
        return car;
    }
}
```

(3)具体建造者
```java
public class BuildBmwCar extends Cars{
    public void buildSteeringWheel(){
        this.car.setBuildSteeringWheel("宝马方向盘");
    }
    
    public void buildWiper(){
        this.car.setWiper("宝马雨刷");
    }
    
    public void buildBrand(){
        this.car.setBrand("宝马");
    }
}

public class BuildBenzCar extends Cars{
    public void buildSteeringWheel(){
        this.car.setBuildSteeringWheel("奔驰方向盘");
    }
    
    public void buildWiper(){
        this.car.setWiper("奔驰雨刷");
    }
    
    public void buildBrand(){
        this.car.setBrand("奔驰");
    }    
}
```

(4)指挥者
```java
public class CarController{
    public Car buildCars(Cars cars){
        cars.buildSteeringWheel();
        cars.buildWiper();
        cars.buildBrand();
        return cars.crateCar();
    }
}
```

(5)测试
```java
public class Test{
    public static void main(String[] args){
      CarController carController = new CarController();
      Cars cars = (Cars)Class.forName("xxx.xx.xx.BuildBenzCar");
      carController.buildCars(cars);
      Class clazz = cars.class;
      Method[] methods = clazz.getMethods();
      java.util.Arrays.stream(methods).foreach(System.out::println);
    }
}
```