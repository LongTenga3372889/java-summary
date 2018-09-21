package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author teng.long@hand-china.com
 * @Name HeapOOM
 * @Description : -Xms20M -Xmx20M -Xmn10M
 * @Date 2018/8/20
 */
public class HeapOOM {
    static class OOMObject{
    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true) {
            list.add(new OOMObject());
        }
    }
}
