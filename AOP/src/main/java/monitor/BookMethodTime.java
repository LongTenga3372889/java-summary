package monitor;

/**
 * @author teng.long@hand-china.com
 * @Name BookMethodTime
 * @Description
 * @Date 2018/10/24
 */
public class BookMethodTime {

    private Long begin;

    private Long end;

    private String methodName;

    public BookMethodTime(String methodName){
        this.methodName = methodName;
        begin = System.currentTimeMillis();
        System.out.println(methodName+"开始时间:"+begin);
    }

    public void printTime(){
        end = System.currentTimeMillis();
        long count = end-begin;
        System.out.println(methodName+"方法一共花费了:"+count+"时间");
    }

}
