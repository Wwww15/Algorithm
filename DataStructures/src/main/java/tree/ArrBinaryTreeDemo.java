package tree;

/**
 * 数据结构：顺序存储二叉树
 * 1.顺序存储二叉树必须是完全二叉树
 * 2.左节点=2^n+1
 * 3.右结点=2^n+2
 * 4.父节点=（n-1）/2
 * 5.n为数组的下标,从0开始
 * 6.树可以转化成数组，数组可以转化成树
 * 7.该结构应用于堆排序
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
//        int arr[] = new int[]{1,2,3,4,5,6,7};
        int arr[] = {1, 3, 6, 8, 10, 14 };
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder(0);
        arrBinaryTree.infixOrder(0);
    }
}

/**
 * 顺序存储二叉树
 */
class  ArrBinaryTree {

    private int arr[];

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     * @param index
     */
    public void preOrder(int index) {
        if(arr == null || arr.length == 0 ) {
            return;
        }
        //中间先遍历
        System.out.println(arr[index]);
        //左边再遍历
        if(2*index+1<arr.length) {
            preOrder(2*index+1);
        }
        //右边再遍历
        if(2*index+2<arr.length) {
            preOrder(2*index+2);
        }
    }

    /**
     * 中序遍历
     * @param index
     */
    public void infixOrder(int index) {
        if(arr == null || arr.length == 0 ) {
            return;
        }
        //先遍历左边
        if(index*2+1<arr.length) {
            infixOrder(index*2+1);
        }
        //输出父节点
        System.out.println(arr[index]);
        //再遍历右边
        if(index*2+2<arr.length) {
            infixOrder(index*2+2);
        }
    }
}


