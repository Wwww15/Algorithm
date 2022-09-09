package kruskal;

import java.util.Arrays;

/**
 * 算法：克鲁斯卡尔算法
 * 1.特点：
 * 1）最小生成树解决方案
 * 2.步骤：
 * 1）按照排序后的权值，将最小的权值逐步添加到树中
 * 2）添加的时候注意不要形成回路
 */
public class KruskalDemo {

    public static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = { { 0, 12, MAX, MAX, MAX, 16, 14},
                { 12, 0, 10, MAX, MAX, 7, MAX},
                { MAX, 10, 0, 3, 5, 6, MAX},
                { MAX, MAX, 3, 0, 4, MAX, MAX},
                { MAX, MAX, 5, 4, 0, 2, 8},
                { 16, 7, 6, MAX, 2, 0, 9},
                { 14, MAX, MAX, MAX, 8, 9, 0}};
        Edge[] kruskal = kruskal(vertexArr, matrix);
        System.out.println(Arrays.toString(kruskal));
    }

    /**
     * 克鲁斯卡尔算法
     * 1.需要一个完整的图对象，包含顶点集合和顶点的邻接矩阵
     * 2.通过邻接矩阵找到对应的边的数组
     * 3.对找到的边的数组进行排序
     * 4.然后对所有的边进行遍历，通过判断是否形成回路，逐步将当前轮最小的边添加到数组中
     * 5.返回数组
     * @param vertex
     * @param matrix
     */
    public static Edge[] kruskal(char[] vertex,int[][] matrix) {
        //先初始化图对象
        Graph graph = new Graph(vertex,matrix);
        //得到边的数组，需要通过边的数组进行权值排序
        Edge[] edgeArr = getEdgeArr(graph);
        //打印查看
//        System.out.println(Arrays.toString(edgeArr));
        //对边进行排序
        sortEdge(edgeArr);
        //打印查看
//        System.out.println(Arrays.toString(edgeArr));
        //克鲁斯卡尔算法的真正开始，添加最小边以及回路的判断
        //最小生成树边的数量
        int minEdgeCount = vertex.length - 1;
        //需要添加的边的数组
        Edge[] minArr = new Edge[minEdgeCount];
        //添加的边的数组的下标
        int index = 0;
        //所有顶点的终点集合
        int[] endArr = new int[vertex.length];
        //依次从小到大遍历，判断是否添加边
        for (int i = 0; i < edgeArr.length;i++) {
            Edge edge = edgeArr[i];
            //边的一个顶点
            int start = edge.getStart();
            //边的另一个顶点
            int end = edge.getEnd();
            //获取start的终点
            int startToEnd = getEnd(endArr, start);
            //获取end的终点
            int endToEnd = getEnd(endArr, end);
            //如果边的两个顶点的终点不一样，就可以添加，避免形成回路
            if(startToEnd != endToEnd) {
                //添加到返回的数组
                minArr[index++] = edge;
                //设置当前start顶点的终点为end，也就是当前的终点的终点为endToEnd，这里设置的前置是，所有的顶点是按照某个特定顺序设定的
                endArr[startToEnd] = endToEnd;
            }
        }
        return minArr;
    }

    /**
     * 得到边对象构成的数组
     * @param graph 图对象
     */
    public static Edge[] getEdgeArr(Graph graph) {
        //初始化边的数组下标index
        int index = 0;
        //初始化边的数组
        Edge[] edgeArr = new Edge[graph.getEdgeCount()];
        int[][] matrix = graph.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            //这里 i+1 因为是无向图，只需要统计一个点到另一点的就行，并且顶点自身不需要加进边的数量里，这里相当于遍历了邻接矩阵的一半
            for (int j = i+1; j < matrix[i].length; j++) {
                //如果存在连接关系
                if(matrix[i][j] != MAX) {
                    edgeArr[index++] = new Edge(i,j,matrix[i][j]);
                }
            }
        }
        return edgeArr;
    }

    /**
     * 对边的数组进行排序，根据边的权值采用冒泡排序，从小到大
     * @param edgeArr
     */
    public static void sortEdge(Edge[] edgeArr) {
        for (int i = 0; i < edgeArr.length-1; i++) {
            for (int j = 0; j < edgeArr.length-i-1; j++) {
                if(edgeArr[j].getWeight() > edgeArr[j+1].getWeight()) {
                    Edge temp = edgeArr[j];
                    edgeArr[j] = edgeArr[j+1];
                    edgeArr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 获取当前顶点的终点
     * 1.利用循环找到最终的终点
     */
    public static int getEnd(int[] end,int vertexIndex) {
        //情况说明：
        //1.不断的循环为了保证当前从低顶点开始，最终能找到最高的顶点（也就是终点），例如：<A,B> <B,C>,这样找A的终点就为C
        //2.不等于0，其一是为了当前顶点自身进来，还是返回自身，其二找到最终的终点
        while(end[vertexIndex] != 0) {
            vertexIndex = end[vertexIndex];
        }
        return vertexIndex;
    }
}

/**
 * 图
 */
class Graph {

    /**
     * 实际上的边的数量
     */
    private int edgeCount;
    /**
     * 顶点数组
     */
    private char[] vertex;
    /**
     * 数据的邻接矩阵，0表示顶点自身,非0和非MAX表示两个顶点之间连接的边的权值，MAX表示 Integer.MAX_VALUE，不存在连接关系
     */
    private int[][] matrix;


    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        //遍历获取到具体的边的数量
        for (int i = 0; i < matrix.length; i++) {
            //这里 i+1 因为是无向图，只需要统计一个点到另一点的就行，并且顶点自身不需要加进边的数量里
            for (int j = i+1; j <matrix[i].length ; j++) {
                if(matrix[i][j] != KruskalDemo.MAX) {
                    edgeCount++;
                }
            }
        }
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

    public char[] getVertex() {
        return vertex;
    }

    public void setVertex(char[] vertex) {
        this.vertex = vertex;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}

/**
 * 边的对应对象
 */
class Edge {

    /**
     * 边的一个顶点
     */
    private int start;
    /**
     * 边的另一个顶点
     */
    private int end;
    /**
     * 边的权值
     */
    private int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "<"+start+","+end+">"+
                "=>" + weight +
                "}";
    }
}
