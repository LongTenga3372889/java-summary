import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author teng.long@hand-china.com
 * @Name Test
 * @Description
 * @Date 2018/10/25
 */
public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = java.util.Date.class;
        //获取所有公有方法
        Method[] methods = clazz.getMethods();
//        java.util.Arrays.stream(methods).forEach(System.out::println);
        //获取所有公有，私有，受保护的方法
        Method[] methods1 = clazz.getDeclaredMethods();
        java.util.Arrays.stream(methods1).forEach(System.out::println);
        //调用
        Method method = clazz.getDeclaredMethod("setDate",int.class);
        method.setAccessible(true);
        Object o = clazz.getConstructor(null).newInstance();
        method.invoke(o,15);
        System.out.println(o);
    }
}
