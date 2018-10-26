import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author teng.long@hand-china.com
 * @Name Test
 * @Description
 * @Date 2018/10/25
 */
public class Test {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = java.util.Date.class;
        //获取所有的公有字段
        Field[] fields = clazz.getFields();
        java.util.Arrays.stream(fields).forEach(field->System.out.println(field));

        //获取所有公有私有受保护的字段
        Field[] fields1 = clazz.getDeclaredFields();
        java.util.Arrays.stream(fields1).forEach(field->System.out.println(field));

        //获取单个字段并调用
        Field field = clazz.getDeclaredField("fastTime");
        Object obj = clazz.getConstructor(null).newInstance();
        field.setAccessible(true);
        field.set(obj,10000);
        java.util.Date date = (Date) obj;
        System.out.println(date);
    }
}
