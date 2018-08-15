/**
 * @author teng.long@hand-china.com
 * @Name VirtualStack
 * @Description
 * @Date 2018/8/15
 */
public class VirtualStack {

    private static int count = 0;

    public static void main(String[] args) {
        VirtualStack virtualStack = new VirtualStack();
        virtualStack.test();
    }

    /**
     * 虚拟机栈内存溢出
     */
    public void test(){
        try {//11854
            count++;
            test();
        }catch (Throwable e){
            System.out.println("递归次数："+count);
            e.printStackTrace();
        }
    }

}
