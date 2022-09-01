package tree;

/**
 * 数据结构与查找算法：二叉树
 * 1.定义：最多只能有两个节点，左节点和右节点
 * 2.二叉树分类：
 *    1）满二叉树：只有最后一层叶子结点并且节点数为2^n-1
 *    2）完全二叉树：只有最后两层有叶子结点，并且倒数第一层左边连续，倒数第二层右边连续
 *
 * 3.遍历与查找方式（主要是中间节点位置的变化，左右节点没有变化）：
 *    1）前序：先遍历中间节点，再遍历左节点，最后遍历右节点
 *    2）中序：先遍历左节点，再遍历中间节点，最后遍历右节点
 *    3）后序：先遍历左节点，再遍历右节点，最后遍历中间节点
 *
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        //创建根节点
        HeroNode root = new HeroNode(1,"宋江");

        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);

        //前序遍历 1,2,3,5,4
        System.out.println("前序遍历");
        binaryTree.preOrder();

        //中序遍历 2,1,5,3,4
        System.out.println("中序遍历");
        binaryTree.infixOrder();

        //后序遍历 2,5,4,3,1
        System.out.println("后序遍历");
        binaryTree.postOrder();

        //前序遍历查找
        System.out.println("前序遍历查找");
        HeroNode heroNode1 = binaryTree.preSearch(5);
        if(heroNode1 != null) {
            System.out.println("前序遍历找到对象:"+heroNode1);
        }else {
            System.out.println("前序遍历没有找到id："+5);
        }


        //中序遍历查找
        System.out.println("中序遍历查找");
        HeroNode heroNode2 = binaryTree.infixSearch(5);
        if(heroNode2 != null) {
            System.out.println("中序遍历找到对象:"+heroNode2);
        }else {
            System.out.println("中序遍历没有找到id："+5);
        }

        //后序遍历查找
        System.out.println("后序遍历查找");
        HeroNode heroNode3 = binaryTree.postSearch(5);
        if(heroNode3 != null) {
            System.out.println("后序遍历找到对象:"+heroNode3);
        }else {
            System.out.println("后序遍历没有找到id："+5);
        }

        //删除结点
        binaryTree.delNode2(3);
        //前序遍历一遍查看
        binaryTree.preOrder();

        

    }

}

/**
 * 二叉树
 */
class  BinaryTree {

    /**
     * 根节点
     */
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if(root != null) {
            this.root.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if(root != null) {
            this.root.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if(root != null) {
            this.root.postOrder();
        }
    }

    /**
     * 前序遍历查找
     */
    public HeroNode preSearch(int id) {
        if(this.root != null) {
            return root.preSearch(id);
        }
        return null;
    }
    /**
     * 中序遍历查找
     */
    public HeroNode infixSearch(int id) {
        if(this.root != null) {
            return root.infixSearch(id);
        }
        return null;
    }

    /**
     * 后序遍历查找
     */
    public HeroNode postSearch(int id) {
        if(this.root != null) {
            return root.postSearch(id);
        }
        return null;
    }

    /**
     * 删除结点
     */
    public void delNode(int id) {
        if(this.root != null) {
            if(this.root.getId() == id) {
                this.root = null;
            }else {
                this.root.delNode(id);
            }
        }
    }

    /**
     * 删除结点规则二
     */
    public void delNode2(int id) {
        if(this.root != null) {
            if(this.root.getId() == id) {
                HeroNode right = this.root.getRight();
                if(this.root.getLeft() != null) {
                    this.root = this.root.getLeft();
                    this.root.setRight(right);
                }else {
                    this.root = this.root.getRight();
                }
            }else {
                this.root.delNode2(id);
            }
        }
    }

}

/**
 * 二叉树结点
 */
class HeroNode {

    /**
     * 编号
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 左边结点
     */
    private HeroNode left;
    /**
     * 右边结点
     */
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历：先中，再左，再右
     */
    public void preOrder(){
        //先遍历中间
        System.out.println(this);
        //再遍历左边
        if(this.left != null) {
            this.left.preOrder();
        }
        //再遍历右边
        if(this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历：先左，再中，再右
     */
    public void infixOrder(){
        //先遍历左边
        if(this.left != null) {
            this.left.infixOrder();
        }
        //再遍历中间
        System.out.println(this);
        //再遍历右边
        if(this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历：先左，再右，再中
     */
    public void postOrder(){
        //先遍历左边
        if(this.left != null) {
            this.left.postOrder();
        }
        //再遍历右边
        if(this.right != null) {
            this.right.postOrder();
        }
        //再遍历中间
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @return
     */
    public HeroNode preSearch(int id) {
        //从中间遍历
        System.out.println("前序遍历");
        if(this.id == id) {
            return this;
        }
        HeroNode heroNode = null;
        //从左边遍历
        if(this.left != null) {
            heroNode = this.left.preSearch(id);
        }
        if(heroNode != null ) {
            return  heroNode;
        }
        //从右边遍历
        if(this.right != null) {
            heroNode = this.right.preSearch(id);
        }
        return heroNode;
    }

    /**
     * 中序遍历查找
     * @return
     */
    public HeroNode infixSearch(int id) {
        HeroNode heroNode = null;
        //从左边遍历
        if(this.left != null) {
            heroNode = this.left.infixSearch(id);
        }
        if(heroNode != null ) {
            return  heroNode;
        }
        //从中间遍历
        System.out.println("中序遍历");
        if(this.id == id) {
            return this;
        }
        //从右边遍历
        if(this.right != null) {
            heroNode = this.right.infixSearch(id);
        }
        return heroNode;
    }

    /**
     * 后序遍历查找
     * @return
     */
    public HeroNode postSearch(int id) {
        HeroNode heroNode = null;
        //从左边遍历
        if(this.left != null) {
            heroNode = this.left.postSearch(id);
        }
        if(heroNode != null ) {
            return  heroNode;
        }
        //从右边遍历
        if(this.right != null) {
            heroNode = this.right.postSearch(id);
        }
        if(heroNode != null ) {
            return  heroNode;
        }
        //从中间遍历
        System.out.println("后序遍历");
        if(this.id == id) {
            return this;
        }
        return heroNode;
    }

    /**
     * 删除结点
     * 1.如果是叶子结点，只删除该结点
     * 2.如果是非叶子节点，删除子树
     * 3.当前删除方式，前序遍历删除
     */
    public void delNode(int id) {
        //判断当前左结点是否删除结点
        if(this.left != null) {
            //找到就返回，并重置左结点
            if(this.left.id == id) {
                this.left = null;
                return;
            } else {
                //没找到就继续递归
                this.left.delNode(id);
            }
        }
        //判断当前右结点是否是删除结点
        if(this.right != null) {
            if(this.right.id == id) {
                //找到就返回，并重置右结点
                this.right = null;
                return;
            }else {
                //没找到就继续递归右结点
                this.right.delNode(id);
            }
        }

    }

    /**
     * 删除结点二
     * 1.如果是叶子结点，只删除该结点
     * 2.如果是非叶子节点
     *  1）当前结点只有一个结点，子结点代替当前结点
     *  2）如果有两个结点，左节点上升代替当前结点
     * 3.当前删除方式，前序遍历删除
     */
    public void delNode2(int id) {
        //判断当前左结点是否删除结点
        if(this.left != null) {
            //找到就返回，并重置左结点
            if(this.left.id == id) {
                HeroNode right = this.left.getRight();
                if(this.left.left != null) {
                    this.left = this.left.left;
                    this.left.setRight(right);
                }else {
                    this.left = this.left.right;
                }
                return;
            } else {
                //没找到就继续递归
                this.left.delNode2(id);
            }
        }
        //判断当前右结点是否是删除结点
        if(this.right != null) {
            if(this.right.id == id) {
                //找到就返回，并重置右结点
                HeroNode right = this.right.getRight();
                if(this.right.left != null) {
                    this.right = this.right.left;
                    this.right.setRight(right);
                }else {
                    this.right = this.right.right;
                }
                return;
            }else {
                //没找到就继续递归右结点
                this.right.delNode2(id);
            }
        }

    }
}
