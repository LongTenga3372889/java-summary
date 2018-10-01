package rabbitMq.controllers;

import error.exception.MyException;
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
            RabbitmqUtil.basicPublish("log","LT","aaa");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    public void rabbitConfirm(){
        try {
            RabbitmqUtil.basicPublisgConfirmLike("log","LongTeng","aaa");
        } catch (MyException e){
            System.out.println("发送异常");
        }catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RabbitProductControllers productControllers = new RabbitProductControllers();
        productControllers.rabbitConfirm();
    }

}
