package rabbitMq.controllers;

import com.rabbitmq.client.*;
import rabbitMq.impl.GetConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author teng.long@hand-china.com
 * @Name RabbitCustomerControllers
 * @Description
 * @Date 2018/7/26
 */
public class RabbitCustomerControllers {

    private Consumer consumer ;

    /**
     * 此模式支持广播
     */
    public void rabbitTest() throws IOException, TimeoutException {
            final Channel channel = GetConnectionFactory.getChannel();
            channel.exchangeDeclare("log", BuiltinExchangeType.FANOUT);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, "log", "");
            //queue:主题 autoAck:是否事物消费
            channel.basicConsume("", false,"myConsumerTag",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    try {
                        //开启事物消费  此方式会大大的降低消费者的效率，但是此模式会保证消息不会因为后续异常而丢失
                        channel.txSelect();
                        long deliveryTag = envelope.getDeliveryTag();
                        channel.basicAck(deliveryTag,false);
                        String s = new String(body);
                        System.out.println(s);
                        //提交事物
                        channel.txCommit();
                    }catch (Exception e){
                        //回滚事物
                        channel.txRollback();
                    }

                }
            });
    }


    public static void main(String[] args) throws IOException, TimeoutException {
        RabbitCustomerControllers rabbitCustomerControllers = new RabbitCustomerControllers();
        rabbitCustomerControllers.rabbitTest();
    }

}
