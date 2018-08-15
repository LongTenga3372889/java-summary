package jvm;

/**
 * @author teng.long@hand-china.com
 * @Name jvm.VirtualMemory
 * @Description
 * @Date 2018/8/14
 */
public class VirtualMemory {

    private void dontStop() {
        while (true) {}
    }

    public void stackLeakByThread() {
        //无限制创建死循环线程
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable{
        VirtualMemory oom = new VirtualMemory();
        oom.stackLeakByThread();
    }

}
