# 什么是RabbitMq
 RabbitMQ是由Erlang语言编写的实现了高级消息队列协议（AMQP）的开源消息代理软件（也可称为 面向消息的中间件）。支持Windows、Linux/Unix、MAC OS X操作系统和包括JAVA在内的多种编程语言。

 AMQP，即Advanced Message Queuing Protocol，一个提供统一消息服务的应用层标准高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计。基于此协议的客户端与消息中间件可传递消息，并不受 客户端/中间件 不同产品，不同的开发语言等条件的限制。
 
# RabbitMQ中的重要概念

（1）Broker：经纪人。提供一种传输服务，维护一条从生产者到消费者的传输线路，保证消息数据能按照指定的方式传输。粗略的可以将图中的RabbitMQ Server当作Broker。

（2）Exchange：消息交换机。指定消息按照什么规则路由到哪个队列Queue。

（3）Queue：消息队列。消息的载体，每条消息都会被投送到一个或多个队列中。

（4）Binding：绑定。作用就是将Exchange和Queue按照某种路由规则绑定起来。

（5）RoutingKey：路由关键字。Exchange根据RoutingKey进行消息投递。

（6）Vhost：虚拟主机。一个Broker可以有多个虚拟主机，用作不同用户的权限分离。一个虚拟主机持有一组Exchange、Queue和Binding。

（7）Producer：消息生产者。主要将消息投递到对应的Exchange上面。一般是独立的程序。

（8）Consumer：消息消费者。消息的接收者，一般是独立的程序。

（9）Channel：消息通道，也称信道。在客户端的每个连接里可以建立多个Channel，每个Channel代表一个会话任务。

# RabbitMQ的使用流程
 AMQP模型中，消息在producer中产生，发送到MQ的exchange上，exchange根据配置的路由方式投递到相应的Queue上，
 Queue又将消息发送给已经在此Queue上注册的consumer，消息从queue到consumer有push和pull两种方式。消息队列的使用过程大概如下：

（1）客户端连接到消息队列服务器，打开一个channel。

（2）客户端声明一个exchange，并设置相关属性。

（3）客户端声明一个queue，并设置相关属性。

（4）客户端使用routing key，在exchange和queue之间建立好Binding关系。

（5）生产者客户端投递消息到exchange。

（6）exchange接收到消息后，就根据消息的RoutingKey和已经设置的binding，进行消息路由（投递），将消息投递到一个或多个队列里。

（7）消费者客户端从对应的队列中获取并处理消息。

# RabbitMQ的优缺点
`优点`：

（1）由Erlang语言开发，支持大量协议：AMQP、XMPP、SMTP、STOMP。

（2）支持消息的持久化、负载均衡和集群，且集群易扩展。

（3）具有一个Web监控界面，易于管理。

（4）安装部署简单，上手容易，功能丰富，强大的社区支持。

（5）支持消息确认机制、灵活的消息分发机制。

`缺点`：

（1）由于牺牲了部分性能来换取稳定性，比如消息的持久化功能，使得RabbitMQ在大吞吐量性能方面不及Kafka和ZeroMQ。

（2）由于支持多种协议，使RabbitMQ非常重量级，比较适合企业级开发。 

# RabbitMQ代码
首先需要引入maven包
```xml
<dependencies>
      <dependency>
          <groupId>com.rabbitmq</groupId>
          <artifactId>amqp-client</artifactId>
          <version>3.5.1</version>
      </dependency>
      <dependency>
          <groupId>org.springframework.amqp</groupId>
          <artifactId>spring-rabbit</artifactId>
          <version>1.4.5.RELEASE</version>
      </dependency>
      <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-api</artifactId>
          <version>1.7.22</version>
      </dependency>
          <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-nop</artifactId>
          <version>1.7.2</version>
      </dependency>
</dependencies>
```
创建一个消息发送者Producer
```java
public class Producer{
    public final static String QUERE_NAME="TEST";
    
    public static void main(String[] args){
         //创建连接工厂
         ConnectionFactory factory = new ConnectionFactory();
         //设置mq服务器的信息
         factory.setHost("ip");
         factory.setUsername("username");
         factory.setPassword("password");
         factory.setPort(2188);
         //创建一个新的连接
         Connection connection = factory.newConnection();
         //创建一个新的通道
         Channel channel = connection.createChannel();
         //申明一个队列
         channel.queueDeclare(QUEUE_NAME, false, false, false, null);
         String message = "Hello RabbitMQ";
         //发送消息到队列中
         channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
         System.out.println("Producer Send +'" + message + "'");
         //关闭连接通道
         channel.close();
         connection.close();
    }
    
}
```

`注1：queueDeclare第一个参数表示队列名称、
第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、
第四个参数为当所有消费者客户端连接断开时是否自动删除队列、
第五个参数为队列的其他参数`

`注2：basicPublish第一个参数为交换机名称、
第二个参数为队列映射的路由key、
第三个参数为消息的其他属性、
第四个参数为发送信息的主体`

创建一个消费者
```java
public class Customer {
    private final static String QUEUE_NAME = "TEST";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("ip");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, false, false, true, null);
        System.out.println("Customer Waiting Received messages");
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };
        //自动回复队列应答 
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```

这就是一个简单的rabbitmq消息的生产和消费例子。

但是在实际运用中会有许多这样那样的问题(如消息的确认机制，消息的广播等等)，以下是我总结的一些方法望大家多多点评。

- [生产者代码](../../mq/src/main/java/rabbitMq/controllers/RabbitProductControllers.java)
- [消费者代码](../../mq/src/main/java/rabbitMq/controllers/RabbitCustomerControllers.java)

``代码中需要注意的地方``

- exchange参数：
当为``topic``时为消息的广播。

当为``direct``时channel绑定的routingkey和发送时的routingkey一样时才可以发送

当为``fanout``时channel只与exchange有关与routingkey无关