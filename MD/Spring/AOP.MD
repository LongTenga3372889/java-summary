# AOP介绍
AOP称为面向切面编程，在程序开发中主要用来解决一些系统层面上的问题，比如日志，事务，权限等待

## AOP的基本慨念

(1)Aspect(切面):通常是一个类，里面可以定义切入点和通知

(2)JointPoint(连接点):程序执行过程中明确的点，一般是方法的调用

(3)Advice(通知):AOP在特定的切入点上执行的增强处理，有before,after,afterReturning,afterThrowing,around

(4)Pointcut(切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式

(5)AOP代理：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类

没有AOP之前的业务逻辑的[代码](../../AOP/src/main/java/Test.java)