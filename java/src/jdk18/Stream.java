package jdk18;

import dto.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author teng.long@hand-china.com
 * @Name Stream  jdk1.8用流来处理集合
 * @Description
 * @Date 2018/9/27
 */
public class Stream {

    User user = new User("lisi", 44);

    /**
     * 初始化一个集合
     */
    private List<User> list = Arrays.asList(
            new User("zhangsan", 11),
            new User("wangwu", 20),
            new User("wangwu", 91),
            new User("zhangsan", 8),
            user,
            user,
            user
     );

    private User[] users = {new User("zhangsan", 11),user};

    /**
     * 利用流来进行遍历
     */
    public void forEach(){
        list.stream().forEach(user -> System.out.println(user.getName()+":"+user.getAge()));
    }

    /**
     * 利用流来进行排序
     */
    public void Sort(){
        list.stream().sorted(Comparator.comparing(User::getAge))
                .forEach(user -> System.out.println(user.getName()+":"+user.getAge()));
    }

    /**
     * 利用流来进行筛选
     */
    public void filter(){
        list.stream().filter(user -> user.getAge()>40).
                sorted(Comparator.comparing(User::getAge)).
                forEach(user -> System.out.println(user.getName()+":"+user.getAge()));
    }

    /**
     * 利用流来筛选最小的4个
     */
    public void limit(){
        list.stream().sorted(Comparator.comparing(User::getAge)).limit(4).
                forEach(user -> System.out.println(user.getName()+":"+user.getAge()));
    }

    /**
     * 利用流来去重复
     * 这个去重复是去除同样的地址的对象。
     */
    public void distinct(){
        list.stream().distinct().
                forEach(user -> System.out.println(user.getName()+":"+user.getAge()));
    }

    /**
     * 利用流对象来获取数组对象的某个属性的平均值最大值最小值等。
     */
    public void math(){
        IntSummaryStatistics math = list.stream().mapToInt(user -> user.getAge()).summaryStatistics();
        System.out.println(math.toString());
    }

    /**
     * 利用流把list对象的某一个属性的值全部大写
     */
    public void map(){
//        list.stream().map(user -> user.getName()).collect(toList()).
//                stream().map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
        list.stream().map(user -> user.getName().toUpperCase()).forEach(User -> System.out.println(user.getName()));
    }

    public void suzhu(){
        Arrays.stream(users).forEach(user-> System.out.println(user.getName()));
    }

    public static void main(String[] args) {
        Stream stream = new Stream();
        stream.suzhu();
    }

}
