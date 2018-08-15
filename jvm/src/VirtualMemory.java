/**
 * @author teng.long@hand-china.com
 * @Name VirtualMemory
 * @Description
 * @Date 2018/8/14
 */
public class VirtualMemory {

    private static int count = 0;

    public static void main(String[] args) {
        VirtualMemory virtualMemory = new VirtualMemory();
        virtualMemory.test();
    }

    /**
     * 虚拟机栈内存溢出
     */
    public void test(){
        try {
            //count++;
            test();
        }catch (Throwable e){
            System.out.println("递归次数："+count);
            e.printStackTrace();
        }
    }

}
