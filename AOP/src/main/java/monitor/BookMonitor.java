package monitor;

/**
 * @author teng.long@hand-china.com
 * @Name BookMonitor
 * @Description
 * @Date 2018/10/24
 */
public class BookMonitor {

    private static ThreadLocal<BookMethodTime> timeThreadLocal = new ThreadLocal<BookMethodTime>();

    public static void begin(String methodName){
        System.out.println("begin:"+methodName);
        BookMethodTime bookMethodTime = new BookMethodTime(methodName);
        timeThreadLocal.set(bookMethodTime);
    }

    public static void end(){
        System.out.println("end");
        BookMethodTime bookMethodTime = timeThreadLocal.get();
        bookMethodTime.printTime();

    }

}
