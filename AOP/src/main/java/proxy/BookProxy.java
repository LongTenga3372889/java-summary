package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author teng.long@hand-china.com
 * @Name BookProxy
 * @Description
 * @Date 2018/10/26
 */
public class BookProxy implements InvocationHandler{

    private Object object;

    public BookProxy(Object object){
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
