package huffmantree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 数据结构：huffman，哈夫曼树，赫夫曼树，霍夫曼树
 * 1.特点：
 *  1) 路径：根节点到相应结点的通路，称为路径
 *  2) 路径长度：通路上的分支数目，称为路径长度如果层级为L，则路径长度为L-1
 *  3) 权值：结点一个有着某种含义的数值
 *  4）带权路径长度：该结点路径长度与权值的乘机
 *  5）树的带权路径长度：树的所有叶子结点的带权路径长度之和
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] random = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTreeNode node = createNode(random);
        preOrder(node);
    }

    /**
     * 前序遍历输出
     */
    public static void  preOrder(HuffmanTreeNode node) {
        if(node != null) {
            node.preOrder();
        }else {
            System.out.println("当前结点为空~");
        }
    }

    /**
     * 创建哈夫曼树
     * 1.将当前的数组排序（这里选择从小到大）
     * 2.将排序后的数组遍历，从最小的开始取值，两个结点合并成一个结点（合并的权值为两个结点之和）
     * 3.分别生成左子节点和右子结点，然后将合并后的结点放入集合中
     * 4.重复1.2.3直到当前的集合中只有一个结点
     */
    public static HuffmanTreeNode createNode(int arr[]) {
        //将数组放入集合中
        ArrayList<HuffmanTreeNode> list = new ArrayList<HuffmanTreeNode>();
        for (int i = 0;i<arr.length;i++) {
            list.add(new HuffmanTreeNode(arr[i]));
        }
        //循环遍历集合生成哈夫曼树
        while (list.size() != 1) {
            //首先对列表进行排序
            list.sort(new Comparator<HuffmanTreeNode>() {
                @Override
                public int compare(HuffmanTreeNode o1, HuffmanTreeNode o2) {
                    return o1.getWeight()-o2.getWeight();
                }
            });
            //取出两个结点
            HuffmanTreeNode node1 = list.get(0);
            HuffmanTreeNode node2 = list.get(1);
            //合并新的结点
            HuffmanTreeNode newNode = new HuffmanTreeNode(node1.getWeight() + node2.getWeight());
            newNode.setLeft(node1);
            newNode.setRight(node2);
            //添加新的结点
            list.add(newNode);
            //移除老的结点
            list.remove(node1);
            list.remove(node2);
        }
        return list.get(0);
    }
}

/**
 * 哈夫曼树结点
 */
class HuffmanTreeNode {

    /**
     * 权值
     */
    int weight;
    /**
     * 左节点
     */
    HuffmanTreeNode left;
    /**
     * 右结点
     */
    HuffmanTreeNode right;

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }

    public HuffmanTreeNode(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public HuffmanTreeNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanTreeNode left) {
        this.left = left;
    }

    public HuffmanTreeNode getRight() {
        return right;
    }

    public void setRight(HuffmanTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanTreeNode{" +
                "weight=" + weight +
                '}';
    }
}
