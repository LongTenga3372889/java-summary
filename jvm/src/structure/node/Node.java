package structure.node;

import error.exception.MyException;

/**
 * @author teng.long@hand-china.com
 * @Name Node
 * @Description
 * @Date 2018/9/20
 */
public class Node {

    /**
     * 链表数据
     */
    private String data;

    /**
     * 链表长度
     */
    private int length;

    /**
     * 下一个节点
     */
    private Node next;

    /**
     * 头节点
     */
    private Node head;

    /**
     * 构造方法初始化时直接创建头节点和第一个数据
     * @param data
     */
    public Node(String data){
        this.data= data;
        head=this;
        length=1;
    }

    /**
     * 在链表末尾加一个节点
     * @param node
     */
    public void addNode(Node node){
        Node temp = head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;
        temp.length = next.length()+node.length();
    }

    public void insertNodeByIndex(int index,Node node){
        if(index>length()+1||index<1){
            throw new MyException("数组下标越界！");
        }
    }

    /**
     * 获取链表长度
     * @return
     */
    public int length(){
        return length;
    }

    public static void main(String[] args) {
        Node node1 = new Node("a");
        Node node2 = new Node("c");
        node2.addNode(new Node("b"));
        node1.addNode(new Node("b"));
        node1.addNode(node2);
    }

}
