package rabbitMq.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author teng.long@hand-china.com
 * @Name GetConnectionFactory
 * @Description
 * @Date 2018/7/25
 */
public class GetConnectionFactory {


    private static String host="10.0.74.180";

    private static String userName="guest";

    private static String passWord="guest";

    private static int port=5672;

    private static ConnectionFactory factory;

    private static Connection connection;

    private static Channel channel;

    private GetConnectionFactory(){}

    public static synchronized Connection getConnection() throws IOException, TimeoutException {
        if (factory == null) {
            factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setUsername(userName);
            factory.setPassword(passWord);
            factory.setPort(port);
            factory.setVirtualHost("/");
        }
        connection = factory.newConnection();
        return connection;
    }

    public static synchronized Channel getChannel() throws IOException, TimeoutException {
        if(connection == null){
            GetConnectionFactory.getConnection();
        }
        channel = connection.createChannel();
        return channel;
    }

}
