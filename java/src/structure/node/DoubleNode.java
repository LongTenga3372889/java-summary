package structure.node;

import error.exception.MyException;
import util.MyObject;

import java.io.*;

/**
 * @author teng.long@hand-china.com
 * @Name DoubleNode
 * @Description 双向链表
 * @Date 2018/9/21
 */
public class DoubleNode extends MyObject implements Serializable {

    private DoubleNode prev;

    private DoubleNode next;

    private String data;

    /**
     * 初始化
     */
    public DoubleNode(){
        this.data = null;
        this.prev = null;
        this.next = null;
    }

    /**
     * 初始化
     * @param data 节点数据
     */
    public DoubleNode(String data){
        this.data = data;
    }

    /**
     * 初始化
     * @param data 节点数据
     * @param prev 上一个结点
     * @param next 下一个节点
     */
    public DoubleNode(String data,DoubleNode prev,DoubleNode next){
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    /**
     * 结算当前节点的长度
     * @return
     */
    public int length(){
        //防止执行length()方法后对本身产生影响
        DoubleNode doubleNode = (DoubleNode) this.clone();
        int length = 1;
        while(doubleNode.next!=null){
            length++;
            doubleNode = doubleNode.next;
        }
        return length;
    }

    /**
     * 增加一个节点
     * @param temp  双向链表
     */
    public void addDobleNode(DoubleNode temp){
        //避免导致节点temp发生变化
        DoubleNode header = this;
        if(temp !=null) {
            //防止方法执行后对temp对象发生变化
            temp = (DoubleNode) temp.clone();
            while (header.next != null) {
                header = header.next;
            }
            header.next = temp;
            temp.prev = header;
        }else{
            throw new MyException("克隆对象为null");
        }
    }

    /**
     * 在双向链表指定的位子插入另一个双向链表
     * @param temp    另一个双向链表
     * @param index   要插入链表的位子
     */
    public void addIndexDobleNode(DoubleNode temp,int index){
        DoubleNode header = this;
        int count = header.length();
        int length;
        //防止执行addIndexDobleNode()后对temp产生影响
        temp = (DoubleNode) temp.clone();
        if(index<1 || index>length()){
            throw new MyException("链表越界异常");
        }
        while((length = header.length())!=0){
            if(index == count - length){
                // 防止抛出空指针异常
                if(header.next!=null) {
                    temp.addDobleNode(header.next);
                }
                header.next = temp;
                temp.prev = header;
                return ;
            }
            header = header.next;
        }
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public static void main(String[] args) {
        DoubleNode aNode = new DoubleNode("aa");
        DoubleNode bNode = new DoubleNode("bb");
        bNode.addDobleNode(aNode);
        aNode.addDobleNode(bNode);
        aNode.addIndexDobleNode(bNode, 1);
        System.out.println(aNode.length());
    }

}
