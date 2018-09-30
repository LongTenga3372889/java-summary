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
    public void rabbitTest(){
        try {
            Channel channel = GetConnectionFactory.getChannel();
            channel.exchangeDeclare("log", BuiltinExchangeType.FANOUT);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, "log", "LT");
            consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
                {
                    //开启事物消费
                    channel.basicConsume("LT", false, consumer);
                    try {
                        String message = new String(body, "UTF-8");
                        System.out.println("consumerTag=" + consumerTag + ",envelope=" + envelope);
                        System.out.println(message);
                        int s = 0;
                        int a = 1/s;
                        //提交事物
                        channel.basicNack(envelope.getDeliveryTag(), false,false);
                    }catch (Exception e){
                        e.printStackTrace();
                        //回滚事物
                        channel.basicNack(envelope.getDeliveryTag(), false, true);
                    }
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        RabbitCustomerControllers rabbitCustomerControllers = new RabbitCustomerControllers();
        rabbitCustomerControllers.rabbitTest();
    }

}
