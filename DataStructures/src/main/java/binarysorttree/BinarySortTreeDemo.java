package binarysorttree;

/**
 * 数据结构：二叉排序树（Binary Sort Tree） BST
 * 1.特点
 * 1) 当前结点表示特殊意义的值比左节点大，比右结点小
 * 2）中序遍历能将结点的值按照有序的方式展示出来
 * 3）查询速度快，删除速度也快
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            tree.addNode(new BinarySortTreeNode(arr[i]));
        }
        //查询结点
//        BinarySortTreeNode binarySortTreeNode = tree.searchNode(7);
//        System.out.println("查询到的结点："+binarySortTreeNode);
        //查询父结点
//        BinarySortTreeNode binarySortTreeParentNode = tree.searchParent(7);
//        System.out.println("查询到的父结点："+binarySortTreeParentNode);
        //删除结点
//        tree.delNode(12);
//        tree.delNode(5);
//        tree.delNode(10);
//        tree.delNode(2);
//        tree.delNode(3);
//        tree.delNode(9);
//        tree.delNode(7);

        //遍历打印查看
        tree.infixOrder();
    }
}

/**
 * 二叉排序树
 */
class BinarySortTree {

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
        } else { //这里大于和等于当前结点的Node插入右边
            //当前右子结点是否为空
            if (this.right == null) {
                this.right = node;
            } else {
                //递归往左子树上添加结点
                this.right.addNode(node);
            }
        }
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

