package avlbinarytree;

/**
 * 数据结构：平衡二叉搜索/排序树（也叫AVL树）
 * 1.特点
 *  1）树的右子树高度和左子树高度的绝对差值不大于1
 *  2）左子树和右子树都是平衡二叉树
 *  3) 平衡二叉树是二叉排序树的升级版，主要为了解决二叉排序树特殊情况下的查询问题，极端情况下二叉排序是单链表，查询效率极低
 *  4）平衡二叉树在二叉排序树的基础上，在添加的时候增加了右旋转、左旋转乃至双旋转
 *  5）右旋转:
 *     a.新建一个结点，结点的值为当前根结点的值
 *     b.新建结点的右子结点为当前结点的右子结点
 *     c.新建结点的左子结点为当前结点的左子结点的右子结点
 *     d.将当前结点的值改为当前左子结点的值
 *     e.当前结点左子结点为左子节点的左子结点
 *     f.当前结点的右子结点为新建结点
 *  6) 左旋转：
 *     a.新建一个结点，结点的值为当前根结点的值
 *     b.新建结点的左子结点为当前结点的左子结点
 *     c.新建结点的右子结点为当前结点的右子结点的左子结点
 *     d.将当前结点的值改为当前右子结点的值
 *     e.当前结点右子结点为右子节点的右子结点
 *     f.当前结点的左子结点为新建结点
 */
public class AVLBinarySortTreeDemo {

    public static void main(String[] args) {
        //左旋转测试
//        int[] arr = {4,3,6,5,7,8};
        //右旋转测试
//        int[] arr = {10,12, 8, 9, 7, 6};
        //双旋转测试
        int[] arr= { 10, 11, 7, 6, 8, 9 };
        AVLBinarySortTree tree = new AVLBinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            tree.addNode(new BinarySortTreeNode(arr[i]));
        }

        //树高度
        int height = tree.getRoot().height();
        System.out.println("树的高度："+height);
        //树左子树高度
        int leftHeight = tree.getRoot().leftHeight();
        System.out.println("树的左子树高度："+leftHeight);
        //树右子树高度
        int rightHeight = tree.getRoot().rightHeight();
        System.out.println("树的右子树高度："+rightHeight);

    }


}


/**
 * 二叉排序树
 */
class AVLBinarySortTree {

    private BinarySortTreeNode root;

    /**
     * 添加结点
     *
     * @param node
     */
    public void addNode(BinarySortTreeNode node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.addNode(node);
        }
    }

    /**
     * 查询结点
     *
     * @param searchValue
     * @return
     */
    public BinarySortTreeNode searchNode(int searchValue) {
        if (this.root != null) {
            return this.root.searchNode(searchValue);
        } else {
            return null;
        }
    }

    /**
     * 查询父节点
     *
     * @param searchValue
     * @return
     */
    public BinarySortTreeNode searchParent(int searchValue) {
        if (this.root == null) {
            return null;
        }
        return this.root.searchParent(searchValue);
    }

    /**
     * 删除结点
     * 1.情况：
     *  1) 删除的是一个叶子结点，将对应的删除结点置为null
     *  2）删除的结点只有一个子结点，四种情况
     *  3）删除的结点有两个结点，将查找删除右子树的最小值或者左子树的最大值
     * 2.首先找到需要删除的结点以及父节点
     * @param delValue
     */
    public void delNode(int delValue) {
        //找到需要删除的结点
        BinarySortTreeNode delNode = searchNode(delValue);
        if(delNode == null) {
            return;
        }
        //找到需要删除的父结点
        BinarySortTreeNode parent = searchParent(delValue);
        //根据情况处理对应的删除
        BinarySortTreeNode delLeft = delNode.getLeft();
        BinarySortTreeNode delRight = delNode.getRight();
        //删除的是一个叶子结点
        if(delLeft == null && delRight== null) {
            //这里有特殊的，如果是根节点,parent = null
            if(parent == null) {
                this.root = null;
                return;
            }
            //直接将对应的结点置空
            //删除的是左节点
            BinarySortTreeNode parentLeft = parent.getLeft();
            if(parentLeft != null && parentLeft.getValue() == delValue) {
                parent.setLeft(null);
            }else {
                //删除的是右结点
                parent.setRight(null);
            }
        }else if(delLeft != null && delRight != null) { //删除的结点有两个子结点
            int value = delMinNode(delRight);
            delNode.setValue(value);
        }else { //删除的结点有一个结点
            //如果当前的结点存在的结点是左节点
            if(delLeft != null) {
                //这里有特殊的，如果是根节点,parent = null
                if(parent == null) {
                    this.root = delLeft;
                    return;
                }
                //当前删除的结点所位于父节点的位置
                //如果是左节点
                BinarySortTreeNode parentLeft = parent.getLeft();
                if(parentLeft != null &&parentLeft.getValue() == delValue) {
                    parent.setLeft(delLeft);
                }else { //如果是右结点
                    parent.setRight(delLeft);
                }
            }else {
                //这里有特殊的，如果是根节点,parent = null
                if(parent == null) {
                    this.root = delRight;
                    return;
                }
                //当前删除的结点所位于父节点的位置
                //如果是左节点
                BinarySortTreeNode parentLeft = parent.getLeft();
                if(parentLeft != null && parentLeft.getValue() == delValue) {
                    parent.setLeft(delRight);
                }else { //如果是右结点
                    parent.setRight(delRight);
                }
            }
        }
    }

    /**
     * 删除树上最小的结点，并返回对应的结点值
     * @return
     */
    public int delMinNode(BinarySortTreeNode node) {
        BinarySortTreeNode temp = node;
        //根据二叉排序树规则，最小的结点肯定在左边或者当前结点
        if(node.getLeft() != null) {
            return delMinNode(temp.getLeft());
        }
        int value = temp.getValue();
        //删除找到的结点
        delNode(value);
        return value;
    }



    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前结点为空~");
        }
    }


    public BinarySortTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinarySortTreeNode root) {
        this.root = root;
    }

}

/**
 * 二叉排序树结点
 */
class BinarySortTreeNode {
    /**
     * 值
     */
    private int value;
    /**
     * 左结点
     */
    private BinarySortTreeNode left;
    /**
     * 右结点
     */
    private BinarySortTreeNode right;


    public BinarySortTreeNode(int value) {
        this.value = value;
    }

    /**
     * 添加结点
     *
     * @param node 要加入树的结点
     */
    public void addNode(BinarySortTreeNode node) {
        if (node == null) {
            return;
        }
        //按照二叉排序树的规则添加，左小右大
        int addValue = node.value;
        //插入左边
        if (addValue < this.value) {
            //当前左子结点是否为空
            if (this.left == null) {
                this.left = node;
            } else {
                //递归往左子树上添加结点
                this.left.addNode(node);
            }
        } else { //插入右边
            //当前右子结点是否为空
            if (this.right == null) {
                this.right = node;
            } else {
                //递归往左子树上添加结点
                this.right.addNode(node);
            }
        }
        //平衡二叉树添加双旋转
        //左旋转
        if(this.rightHeight()-this.leftHeight()>1) {
            //左旋转前，判断对应的右子结点的左子树的高度是否大于右子树，防止转换平衡二叉树失败
            if(this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                //如果确实右子结点的左子树高度大于右子树高度，当前结点右子树右旋转
                this.right.rightRotate();;
            }
            this.leftRotate();
            return;
        }

        //平衡二叉树添加双旋转
        //右旋转
        if(this.leftHeight()-this.rightHeight()>1) {
            //右旋转前，判断对应的左子结点的右子树的高度是否大于左子树，防止转换平衡二叉树失败
            if(this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //如果确实左子结点的右子树高度大于左子树高度，当前结点左子树左旋转
                this.left.leftRotate();;
            }
            //平衡二叉树添加双旋转
            this.rightRotate();
            return;
        }
    }

    /**
     * 左子树高度
     * @return
     */
    public int leftHeight(){
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    /**
     * 右子树高度
     * @return
     */
    public int rightHeight(){
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }


    /**
     * 获取当前树的高度
     * @return
     */
    public int height() {
        return Math.max(this.left == null ?0:this.left.height(),this.right == null ?0:this.right.height())+1;
    }

    /**
     * 左旋转
     * 1.记住往哪边转，新节点就添加在哪边
     * 2.相反的子结点就需要重新往上升，提为根结点
     */
    public void leftRotate() {
        //新疆一个值为当前结点的新结点
        BinarySortTreeNode newNode = new BinarySortTreeNode(this.value);
        //新建结点的左子结点为当前的左子结点
        newNode.left = this.left;
        //新建结点的右子结点为当前结点的右子结点的左子结点
        newNode.right = this.right.left;
        //当前结点值为右子结点的值
        this.value = this.right.value;
        //当前结点的左子结点为新建结点
        this.left = newNode;
        //当前结点的右子结点为右子结点的右子结点
        this.right = this.right.right;
    }


    /**
     * 右旋转
     * 1.记住往哪边转，新节点就添加在哪边
     * 2.相反的子结点就需要重新往上升，提为根结点
     */
    public void rightRotate() {
        //新建一个值为当前结点的新结点
        BinarySortTreeNode newNode = new BinarySortTreeNode(this.value);
        //新建结点的右子结点为当前结点的右子结点
        newNode.right = this.right;
        //新建结点的左子结点为当前结点的左子结点的右子结点
        newNode.left = this.left.right;
        //当前结点的值为左子结点的值
        this.value = this.left.value;
        //当前结点的左子结点为左子结点的左子结点
        this.left = this.left.left;
        //当前结点的右子结点为新建结点
        this.right = newNode;
    }

    /**
     * 查询结点
     */
    public BinarySortTreeNode searchNode(int searchValue) {
        //如果等于当前结点
        if (this.value == searchValue) {
            return this;
        }
        //是否在左子树
        if (searchValue < this.value) {
            //左子树不为空
            if (this.left != null) {
                //向左递归查询
                return this.left.searchNode(searchValue);
            } else {
                //左子树空，直接返回null
                return null;
            }
        } else {
            //右子树不为空
            if (this.right != null) {
                //向右递归查询
                return this.right.searchNode(searchValue);
            } else {
                //右子树空，直接返回null
                return null;
            }
        }
    }

    /**
     * 查询父节点
     *
     * @param searchValue
     * @return
     */
    public BinarySortTreeNode searchParent(int searchValue) {
        //如果左结点和右结点的值等于需要查找的值
        if ((this.left != null && this.left.value == searchValue) || (this.right != null && this.right.value == searchValue)) {
            return this;
        } else {
            if (searchValue < this.value) {
                //判断左结点是否有值
                if (this.left != null) {
                    //是否向左继续递归查询
                    return this.left.searchParent(searchValue);
                }
            } else {
                //判断右结点是否有值
                if (this.right != null) {
                    //是否向左继续递归查询
                    return this.right.searchParent(searchValue);
                }
            }
            return null;
        }
    }


    /**
     * 遍历结点（中序遍历）
     */
    public void infixOrder() {
        //先左
        if (this.left != null) {
            this.left.infixOrder();
        }
        //再中间
        System.out.println(this);
        //再右
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinarySortTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinarySortTreeNode left) {
        this.left = left;
    }

    public BinarySortTreeNode getRight() {
        return right;
    }

    public void setRight(BinarySortTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                '}';
    }
}



