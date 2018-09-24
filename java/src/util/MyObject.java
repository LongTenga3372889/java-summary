package util;

import error.exception.MyException;
import structure.node.Node;

import java.io.*;

/**
 * @author teng.long@hand-china.com
 * @Name MyObject
 * @Description
 * @Date 2018/9/24
 */
public class MyObject {

    @Override
    public Object clone() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(this);
            bi = new ByteArrayInputStream(bo.toByteArray());
            oi = new ObjectInputStream(bi);
            return oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(oi!=null) {
                    oi.close();
                }if(bi!=null) {
                    bi.close();
                }if(oo!=null) {
                    oo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new MyException("clone出现异常！");
    }

}
