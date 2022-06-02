package huffmancode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 算法：哈夫曼编码
 * 1.特点：
 *  1)利用哈夫曼树完成对数据的压缩，相似度越高，压缩度越高
 */
public class HuffmanCode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        zip(str);
    }

    /**
     * 压缩数据
     * 1.首先将字符串数据转成byte数组
     * 2.统计byte数据中的value个数
     * 3.将统计个数的Map转成list<HuffmanTreeNode>
     * 4.将list<HuffmanTreeNode>转成HuffmanTreeNode(哈夫曼树)
     * 5.遍历哈夫曼树生成对应的二进制字符串
     * 6.将二进制字符串压缩成byte数组
     * @param data
     */
    public static void zip(String data) {
        //获取待压缩的原byte数组
        byte[] srcBytes = data.getBytes();
        //统计对应的byte个数
        Map<Byte, Integer> countMap = new HashMap<>();
        for (byte item : srcBytes) {
            Integer count = countMap.get(item);
            if(count == null) {
                count = 0;
            }
            countMap.put(item,count+1);
        }
        //将统计着byte个数的map转成list<HuffmanTreeNode>
        List<HuffmanTreeNode> nodeList = countMap.entrySet().stream().parallel().map(item -> new HuffmanTreeNode(item.getValue(), item.getKey())).collect(Collectors.toList());
        //构建huffman树
        HuffmanTreeNode huffmanTreeNode = createHuffmanNode(nodeList);
        //谦虚打印生成的哈夫曼树
//        preOrder(huffmanTreeNode);
        //生成对应的哈夫曼编码表
        HashMap<Byte, String> huffmanCodeMap = new HashMap<>();
        huffmanCodeMap(huffmanTreeNode,"",new StringBuilder(),huffmanCodeMap);
        //遍历原字节数组生成编码后的huffman字符串（二进制数组）
        StringBuilder huffmanCodeStr = new StringBuilder();
        for (byte item : srcBytes) {
            huffmanCodeStr.append(huffmanCodeMap.get(item));
        }
        //真正的开始压缩
        byte[] zipByte = doZip(huffmanCodeStr);
        System.out.println(Arrays.toString(zipByte));
    }

    /**
     * 创建huffmantree
     * 1.首先排序当前的list
     * 2.从头取结点合并为新的结点，然后将该结点放入集合中
     * 3.反复操作1.2直到当前集合中的size不大于1
     * @return
     */
    public static HuffmanTreeNode createHuffmanNode(List<HuffmanTreeNode> nodeList) {
        while (nodeList.size() > 1) {
            //先排序
            Collections.sort(nodeList);
            //获取最小的结点
            HuffmanTreeNode leftNode = nodeList.get(0);
            HuffmanTreeNode rightNode = nodeList.get(1);
            //创建新的合并结点
            HuffmanTreeNode parent = new HuffmanTreeNode(leftNode.getWeight() + rightNode.getWeight(), null);
            //再合并新的结点
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            //移除原来集合里面的结点
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            //添加新的结点
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    /**
     * 生成huffman编码表
     * 1.遍历当前的哈夫曼树
     * 2.根据当前的结点value判断是否是叶子结点
     * 3.是叶子结点，结束递归
     * 4.不是叶子节点，继续递归
     * @param node         哈夫曼树结点
     * @param code         当前是左还是右
     * @param huffmancCode 到根节点的路径
     * @param
     */
    public static void huffmanCodeMap(HuffmanTreeNode node, String code, StringBuilder huffmancCode,Map<Byte,String> huffmanCodeMap) {
        StringBuilder newHuffmanCode = new StringBuilder(huffmancCode);
        if (node != null) {
            //路径叠加
            newHuffmanCode.append(code);
            if(node.getValue() == null)  {
                //继续向左递归
                huffmanCodeMap(node.getLeft(),"0",newHuffmanCode,huffmanCodeMap);
                //继续向右递归
                huffmanCodeMap(node.getRight(),"1",newHuffmanCode,huffmanCodeMap);
            }
            else {
                huffmanCodeMap.put(node.getValue(),newHuffmanCode.toString());
            }
        }
    }

    /**
     * 真正的开始压缩
     * 1.将二进制字符串转化为字节数组
     * @param builder 哈夫曼编码字符串
     * @return 压缩后的字节数组
     */
    public static byte[] doZip(StringBuilder builder)  {
        int len;
        if(builder.length() % 8 == 0 ) {
            len = builder.length() /8;
        }else {
            len = builder.length() /8 +1;
        }
        byte[] zipByte = new byte[len];
        int index = 0;
        for (int i = 0;i<builder.length();i+=8) {
            String binaryTemp;
            if(i + 8> builder.length()) {
                binaryTemp  = builder.substring(i);
            }else {
                binaryTemp  = builder.substring(i, i + 8);
            }
            byte b = (byte) Integer.parseInt(binaryTemp, 2);
            zipByte[index++] = b;
        }
        return zipByte;
    }
    /**
     * 前序遍历
     */
    public static void preOrder(HuffmanTreeNode node) {
        if(node != null) {
            node.preOrder();
        }
    }
}

/**
 * 哈夫曼数结点
 */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{

    /**
     * 权值（出现的次数）
     */
    private Integer weight;
    /**
     * 对应的ASCII值
     */
    private Byte value;
    /**
     * 左子结点
     */
    private HuffmanTreeNode left;
    /**
     * 右子结点
     */
    private HuffmanTreeNode right;

    public HuffmanTreeNode(Integer weight, Byte value) {
        this.weight = weight;
        this.value = value;
    }

    /**
     * 前序遍历
     * @param
     */
    public void preOrder() {
        System.out.println(this.getValue());
        if(this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if(this.getRight() != null) {
            this.getRight().preOrder();
        }
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
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
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return this.weight-o.weight;
    }
}
