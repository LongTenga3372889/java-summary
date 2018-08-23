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
