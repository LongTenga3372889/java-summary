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

    public int length(){
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
     * @param temp
     */
    public void addDobleNode(DoubleNode temp){
        //避免导致节点temp发生变化
        DoubleNode header = this;
        temp = (DoubleNode) temp.clone();
        while(header.next !=null){
            header = header.next;
        }
        header.next = temp;
        temp.prev = header;
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
        aNode.addDobleNode(bNode);
        System.out.println(aNode.length());
    }

}
