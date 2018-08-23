package jvm;

/**
 * @author teng.long@hand-china.com
 * @Name VMStackError
 * @Description
 * @Date 2018/8/23
 */
public class VMStackError {

    private int stackLength=1;

    public void stack() {
        stackLength++;
        stack();
    }

    /**
     * VM Xss:128K 深度：999
     * Xss:256K    深度：2785
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable{
        VMStackError j = new VMStackError();
        try{
            j.stack();
        }catch(Throwable e) {
            System.out.println(j.stackLength);
            throw e;
        }
    }
}
