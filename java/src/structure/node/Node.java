package structure.node;

import error.exception.MyException;
import util.MyObject;

import java.io.*;

/**
 * @author teng.long@hand-china.com
 * @Name 单链表Node
 * @Description
 * @Date 2018/9/20
 */
public class Node  extends MyObject implements Serializable {

    /**
     * 链表数据
     */
    private Object data;

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
     *
     * @param data
     */
    public Node(Object data) {
        this.data = data;
        head = this;
    }

    /**
     * 在链表末尾加一个节点
     *
     * @param node
     */
    public void addNode(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 在指定的位子插入链表
     *
     * @param index
     * @param node
     */
    public void insertNodeByIndex(int index, Node node) {
        if (index > length() + 1 || index < 1) {
            throw new MyException("数组下标越界！");
        }
        Node temp = head;
        //为了不影响node链表
        Node node1 = (Node) node.clone();
        while (temp.next != null) {
            if (index == temp.length()) {
                node1.addNode(temp.next);
                temp.next = node1;
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 删除指定位子的节点
     *
     * @param index
     */
    public void deleteNode(int index) {
        if (index < 1 || index > length()) {
            throw new MyException("数组下标越界！");
        }
        Node temp = head;
        int length = 1;
        while (temp.next != null) {
            if (index == length++) {
                temp.next = temp.next.next;
            }
            temp = temp.next;
        }
    }

    /**
     * 获取链表长度
     *
     * @return
     */
    public int length() {
        int length = 0;
        Node temp = head;
        try {
            while (temp.next != null) {
                length++;
                temp = temp.next;
            }
        } catch (Exception e) {
            throw new MyException("链表头尾重合！");
        }
        return length;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object date){
        this.data = date;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public static void main(String[] args) {
        Node node1 = new Node("a");
        Node node2 = new Node("c");
        node2.addNode(new Node("b"));
        node1.addNode(new Node("b"));
        node1.insertNodeByIndex(1, node2);
        node2.insertNodeByIndex(1, node1);
        node2.deleteNode(2);
        node1.deleteNode(2);
    }

}
