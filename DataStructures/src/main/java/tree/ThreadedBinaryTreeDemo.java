package tree;

/**
 * 线索二叉树
 * 1.特点：
 *     1）n个结点的二叉树含有n+1个空指针域
 *     2）线索的分为前序线索二叉树、中序线索二叉树、后序线索二叉树
 *     3) 结点分为前驱和后继结点
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);
        TreeNode node10 = new TreeNode(10);
        TreeNode node14 = new TreeNode(14);

        node1.setLeft(node3);
        node1.setRight(node6);
        node3.setLeft(node8);
        node3.setRight(node10);
        node6.setLeft(node14);

        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree(node1);
        //线索化当前的树
        binaryTree.threadedTree();
        //查看当前线索化后的树
        binaryTree.threadedList();
    }

}


/**
 * 线索二叉树
 */
class ThreadedBinaryTree {

    /**
     * 根节点
     */
    private TreeNode root;
    /**
     * 为了实现线索化，需要记录前驱结点
     */
    private TreeNode pre;

    public ThreadedBinaryTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * 线索化二叉树
     */
    public void threadedTree() {
        threadedTree(root);
    }

    /**
     * 线索化当前的树(中序遍历)
     * 1.中序遍历就是先遍历左结点，再遍历父节点，再遍历右结点
     * @param node
     */
    public void threadedTree(TreeNode node) {
        if(node == null) {
            return;
        }
        //线索化左子树
        threadedTree(node.getLeft());

        //线索化当前的结点
        if(node.getLeft() == null ) {
            //设置前驱结点
            node.setLeft(pre);
            //设置前驱结点的类型,0为正常结点，1为线索结点
            node.setLeftType(1);
        }
        //判断前驱结点的右结点
        if(pre != null && pre.getRight() == null) {
            //设置前驱结点的后继结点
            pre.setRight(node);
            //设置后继结点的类型,0为正常结点，1为线索结点
            pre.setRightType(1);
        }
        //处理完当前结点转化，方便后继结点使用
        pre = node;
        //线索化右子树
        threadedTree(node.getRight());
    }

    /**
     * 遍历当前结点（中序遍历）
     * 1.最好Debug看看
     */
    public void threadedList() {
        TreeNode node = root;
        while (node != null) {
            //找到当前遍历开始前的根节点，也就是从左开始遍历
            while(node.getLeftType() == 0) {
                node =  node.getLeft();
            }
            //输出当前结点，中间结点
            System.out.printf(node.getId()+"\t");
            //后序结点(防止死循环)
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.printf(node.getId()+"\t");
            }
            node = node.getRight();
        }
    }
}


/**
 * 树结点
 */
class TreeNode {

    private int id;

    private TreeNode left;

    private TreeNode right;

    /**
     * 用于区分当前左结点的类型,0正常结点,1线索结点
     */
    private int leftType;
    /**
     * 用于区分当前右结点的类型,0正常结点,1线索结点
     */
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}
