package structure.node;

/**
 * @author teng.long@hand-china.com
 * @Name DoubleNode
 * @Description 双向链表
 * @Date 2018/9/21
 */
public class DoubleNode {

    private DoubleNode prev;

    private DoubleNode next;

    private String data;

    public DoubleNode(String data){
        this.data = data;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }



}
