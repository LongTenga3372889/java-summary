package rabbitMq.controllers;

import rabbitMq.util.RabbitmqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author teng.long@hand-china.com
 * @Name RabbitTest
 * @Description
 * @Date 2018/7/25
 */
public class RabbitProductControllers {

    public void rabbitTest(){
        try {
            RabbitmqUtil.basicPublish("LT", "aaa");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        RabbitProductControllers productControllers = new RabbitProductControllers();
        productControllers.rabbitTest();
    }

}
