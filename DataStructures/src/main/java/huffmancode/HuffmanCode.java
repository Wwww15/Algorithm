package huffmancode;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 算法：哈夫曼编码
 * 1.特点：
 * 1)利用哈夫曼树完成对数据的压缩，相似度越高，压缩度越高
 */
public class HuffmanCode {

    private static Map<Byte, String> huffmanCodeMap = null;

    public static void main(String[] args) {
//        String str = "i like like like java do you like a java";
//        byte[] zipByte = zip(str.getBytes());
//        byte[] unzipByte = unzip(zipByte, huffmanCodeMap);
//        System.out.println(new String(unzipByte));
        String srcPath = "C:/Users/wenzx/Desktop/test.jpg";
        String dstPath1 = "C:/Users/wenzx/Desktop/test.zip";
        String dstPath2 = "C:/Users/wenzx/Desktop/test1.jpg";
//        zipFile(srcPath,dstPath1);
        unzipFile(dstPath1,dstPath2);
    }

    /**
     * 压缩数据
     * 1.首先将字符串数据转成byte数组
     * 2.统计byte数据中的value个数
     * 3.将统计个数的Map转成list<HuffmanTreeNode>
     * 4.将list<HuffmanTreeNode>转成HuffmanTreeNode(哈夫曼树)
     * 5.遍历哈夫曼树生成对应的二进制字符串
     * 6.将二进制字符串压缩成byte数组
     *
     * @param srcBytes
     */
    public static byte[] zip(byte[] srcBytes) {
        //统计对应的byte个数
        Map<Byte, Integer> countMap = new HashMap<>();
        for (byte item : srcBytes) {
            Integer count = countMap.get(item);
            if (count == null) {
                count = 0;
            }
            countMap.put(item, count + 1);
        }
        //将统计着byte个数的map转成list<HuffmanTreeNode>
        List<HuffmanTreeNode> nodeList = countMap.entrySet().stream().parallel().map(item -> new HuffmanTreeNode(item.getValue(), item.getKey())).collect(Collectors.toList());
        //构建huffman树
        HuffmanTreeNode huffmanTreeNode = createHuffmanNode(nodeList);
        //谦虚打印生成的哈夫曼树
//        preOrder(huffmanTreeNode);
        //生成对应的哈夫曼编码表
        huffmanCodeMap = new HashMap<>();
        huffmanCodeMap(huffmanTreeNode, "", new StringBuilder(), huffmanCodeMap);
        //遍历原字节数组生成编码后的huffman字符串（二进制数组）
        StringBuilder huffmanCodeStr = new StringBuilder();
        for (byte item : srcBytes) {
            huffmanCodeStr.append(huffmanCodeMap.get(item));
        }
        //真正的开始压缩
        byte[] zipByte = doZip(huffmanCodeStr);
        return zipByte;
    }

    /**
     * 压缩文件
     *
     * @param srcPath
     * @param dstPath
     */
    public static void zipFile(String srcPath, String dstPath) {
        FileInputStream is = null;
        ObjectOutputStream oos = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(srcPath);
            byte[] temp = new byte[is.available()];
            is.read(temp);
            os = new FileOutputStream(dstPath);
            oos = new ObjectOutputStream(os);
            //压缩文件
            byte[] zipByte = zip(temp);
            //输出压缩文件
            oos.writeObject(zipByte);
            //输出哈夫曼编码
            oos.writeObject(huffmanCodeMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 创建huffmantree
     * 1.首先排序当前的list
     * 2.从头取结点合并为新的结点，然后将该结点放入集合中
     * 3.反复操作1.2直到当前集合中的size不大于1
     *
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
     *
     * @param node         哈夫曼树结点
     * @param code         当前是左还是右
     * @param huffmancCode 到根节点的路径
     * @param
     */
    public static void huffmanCodeMap(HuffmanTreeNode node, String code, StringBuilder huffmancCode, Map<Byte, String> huffmanCodeMap) {
        StringBuilder newHuffmanCode = new StringBuilder(huffmancCode);
        if (node != null) {
            //路径叠加
            newHuffmanCode.append(code);
            if (node.getValue() == null) {
                //继续向左递归
                huffmanCodeMap(node.getLeft(), "0", newHuffmanCode, huffmanCodeMap);
                //继续向右递归
                huffmanCodeMap(node.getRight(), "1", newHuffmanCode, huffmanCodeMap);
            } else {
                huffmanCodeMap.put(node.getValue(), newHuffmanCode.toString());
            }
        }
    }

    /**
     * 真正的开始压缩
     * 1.将二进制字符串转化为字节数组
     *
     * @param builder 哈夫曼编码字符串
     * @return 压缩后的字节数组
     */
    public static byte[] doZip(StringBuilder builder) {
        int len;
        if (builder.length() % 8 == 0) {
            len = builder.length() / 8;
        } else {
            len = builder.length() / 8 + 1;
        }
        byte[] zipByte = new byte[len];
        int index = 0;
        for (int i = 0; i < builder.length(); i += 8) {
            String binaryTemp;
            if (i + 8 > builder.length()) {
                binaryTemp = builder.substring(i);
            } else {
                binaryTemp = builder.substring(i, i + 8);
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
        if (node != null) {
            node.preOrder();
        }
    }

    /**
     * 解压对应的byte数组
     * 1.将压缩转换为对应的二进制字符串
     * 2.将二进制字符串转成原数据数组
     *
     * @param zipByte        压缩数据数组
     * @param huffmanCodeMap 哈夫曼编码表
     * @return
     */
    public static byte[] unzip(byte[] zipByte, Map<Byte, String> huffmanCodeMap) {
        //临时存储二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < zipByte.length; i++) {
            boolean flag = (i == zipByte.length - 1);
            //将遍历的二进制字符串拼接，最后一个byte不需要补位
            stringBuilder.append(byteToBitString(!flag, zipByte[i]));
        }
        //将哈夫曼编码表进行key和value的转换
        Map<String, Byte> huffmanCode = huffmanCodeMap.entrySet().stream().parallel().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        //遍历字符串进行匹配，并将匹配后的值进行存储
        ArrayList<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            //当前遍历到的count,表示从当前i的位置开始前移数量
            Boolean flag = true;
            int count = 1;
            while (flag) {
                int end = i + count;
                //截取到的二进制字符串
                String binaryStr = "";
                if(end>stringBuilder.length()) {
                    binaryStr = stringBuilder.substring(i);
                    i += count;
                    flag = false;
                }else {
                    binaryStr = stringBuilder.substring(i, end);
                }
                //是否存在二进制字符串对应的value
                Byte resultByte = huffmanCode.get(binaryStr);
                //判断进行处理
                if (resultByte == null) {
                    count++;
                } else {
                    byteList.add(resultByte);
                    i += count;
                    flag = false;
                }
            }
        }
        //将list集合转成数组
        byte[] srcByte = new byte[byteList.size()];
        for (int i = 0; i < srcByte.length; i++) {
            srcByte[i] = byteList.get(i);
        }
        return srcByte;
    }

    /**
     * 解压文件
     *
     * @param srcPath
     * @param dstPath
     */
    public static void unzipFile(String srcPath, String dstPath) {
        FileInputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(srcPath);
            ois = new ObjectInputStream(is);
            //获取到压缩数据
            byte[] zipByte = (byte[]) ois.readObject();
            //获取到哈夫曼编码表
            Map<Byte,String> huffmanCode = (Map<Byte, String>) ois.readObject();
            //解压缩
            byte[] srcByte = unzip(zipByte, huffmanCode);
            os = new FileOutputStream(dstPath);
            os.write(srcByte);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将byte数据转成对应的二进制字符
     * 1.byte转化时，8位二进制，注意正数的高位补零
     * 2.注意最后一位不需要补零，但是需要截取
     *
     * @param b    当前需要转换的byte值
     * @param flag 是否是最后的标识
     * @return
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        //正数需要在高位补零，方便后面统一截取
        if (flag || b > 0) {
            temp |= 256;
        }
        //转成二进制字符串
        String str = Integer.toBinaryString(temp);
        //只要8位二进制
        if (flag || b < 0) {
            str = str.substring(str.length() - 8);
        }
        return str;
    }

}

/**
 * 哈夫曼数结点
 */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {

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
     *
     * @param
     */
    public void preOrder() {
        System.out.println(this.getValue());
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
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
        return this.weight - o.weight;
    }
}
