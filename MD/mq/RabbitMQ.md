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

- [生产者代码](../../mq/src/rabbitMq/controllers/RabbitProductControllers.java)
- [消费者代码](../../mq/src/rabbitMq/controllers/RabbitCustomerControllers.java)
