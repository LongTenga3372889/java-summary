package rabbitMq.util;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import rabbitMq.impl.GetConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author teng.long@hand-china.com
 * @Name RabbitmqUtil
 * @Description
 * @Date 2018/7/26
 */
public class RabbitmqUtil {

    /**
     * 消息指定到消费者   direct方式
     * @param topic    消息管道
     * @param message  消息
     * @throws IOException
     * @throws TimeoutException
     */
    public static void basicPublish(String topic,String message) throws IOException, TimeoutException {
        Channel channel = GetConnectionFactory.getChannel();
        channel.queueDeclare(topic, false, false, false, null);
        channel.basicPublish("", topic, null, message.getBytes("UTF-8"));
    }

    /**
     * 消息的广播        fanout方式
     * @param exchange 申明交易所
     * @param topic    消息管道
     * @param message  消息
     * @throws IOException
     * @throws TimeoutException
     */
    public static void basicPublish(String exchange,String topic,String message) throws IOException, TimeoutException {
        Channel channel = GetConnectionFactory.getChannel();
        channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT);
        channel.basicPublish(exchange, topic, null, message.getBytes("UTF-8"));
    }

    /**
     * 消息的匹配广播      topic方式
     * @param exchange  申明交易所
     * @param topic     消息管道
     * @param message   消息
     * @throws IOException
     * @throws TimeoutException
     */
    public static void basicPublishLike(String exchange,String topic,String message) throws IOException, TimeoutException {
        Channel channel = GetConnectionFactory.getChannel();
        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);
        channel.basicPublish(exchange, topic, null, message.getBytes("UTF-8"));
    }

}
