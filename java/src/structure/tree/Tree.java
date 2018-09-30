package structure.tree;

/**
 * @author teng.long@hand-china.com
 * @Name Tree  二叉树
 * @Description
 * @Date 2018/9/26
 */
public class Tree {

    private String data;

    /**
     * 孩子节点 左
     */
    private Tree leftTree;

    /**
     * 孩子节点 右
     */
    private Tree rightTree;

    /**
     * 深度
     */
    private int length;

    /**
     * 是否为根节点
     */
    private boolean rootTree;

    /**
     * 该树下可插入的位子，树的插入指针
     */
    private int pointerTree;

    /**
     * 构造方法
     * @param data
     */
    public Tree(String data){
        Tree left = new Tree(null,null,null);
        Tree right = new Tree(null,null,null);
        this.leftTree = left;
        this.rightTree = right;
        this.data = data;
        this.rootTree = true;
        pointerTree = 1;
    }

    /**
     * 构造方法
     * @param data
     */
    public Tree (Tree rightTree,Tree leftTree ,String data){
        this.rightTree = rightTree;
        this.leftTree = leftTree;
        this.data = data;
    }

    /**
     * 数下面增加一个元素
     * @param data
     */
    public void addTree(String data){
        Tree tree = new Tree(data);
    }

}
