package jvm.calculate;

/**
 * @author teng.long@hand-china.com
 * @Name ReferenceCountingGC
 * @Description -verbose:gc -Xmx20M -XX:MaxDirectMemorySize=10M -XX:+PrintGCDetails
 * @Date 2018/9/5
 */
public class ReferenceCountingGC {

    public Object instance=null;
    private static final int MB=1024*1024;
    private byte[]bigSize=new byte[5*MB];
    public void testGC(){
        ReferenceCountingGC objA=new ReferenceCountingGC();
        ReferenceCountingGC objB=new ReferenceCountingGC();
        objA.instance=objB;
        objB.instance=objA;
        //假设在这行发生GC,objA和objB是否能被回收？
        objB=null;
        objA=null;
        System.gc();
    }

    public static void main(String args[]) {
        ReferenceCountingGC gc = new ReferenceCountingGC();
        gc.testGC();
    }

}
