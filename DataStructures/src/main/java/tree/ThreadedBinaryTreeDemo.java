package tree;

/**
 * 线索二叉树
 * 1.特点：
 *     1）n个结点的二叉树含有n+1个空指针域
 *     2）线索的分为前序线索二叉树、中序线索二叉树、后序线索二叉树
 *     3) 结点分为前驱和后继结点
 */
public class ThreadedBinaryTreeDemo {


}


/**
 * 线索二叉树
 */
class ThreadedBinaryTree {

    private TreeNode root;

    public ThreadedBinaryTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}


/**
 * 树结点
 */
class TreeNode {

    private int id;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
