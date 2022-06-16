package dijkstra;

import java.util.Arrays;

/**
 * 算法：迪杰斯特拉算法
 * 1.特点：
 *  1）解决最短路径
 *  2）利用广度优先遍历来找到多个顶点的最短路径
 *  3）图graph用来存储当前的顶点与顶点的数据信息
 *  4）顶点的访问、距离、前缀等信息，需要一个vertexInfo存储
 */
public class DijkstraDemo {

    public static final int MAX = 65535;

    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0]=new int[]{MAX,5,7,MAX,MAX,MAX,2};
        matrix[1]=new int[]{5,MAX,MAX,9,MAX,MAX,3};
        matrix[2]=new int[]{7,MAX,MAX,MAX,8,MAX,MAX};
        matrix[3]=new int[]{MAX,9,MAX,MAX,MAX,4,MAX};
        matrix[4]=new int[]{MAX,MAX,8,MAX,MAX,5,4};
        matrix[5]=new int[]{MAX,MAX,MAX,4,5,MAX,6};
        matrix[6]=new int[]{2,3,MAX,MAX,4,6,MAX};
        dijkstra(vertex,matrix,2);
    }

    /**
     * 迪杰斯特拉算法实践
     * @param vertex 顶点信息
     * @param matrix 顶点与顶点数据信息
     * @return
     */
    public static void dijkstra(char[] vertex,int[][] matrix,int start) {
        //初始化图
        Graph graph = new Graph(vertex,matrix);
        //初始化顶点访问等信息对象
        VertexInfo vertexInfo = new VertexInfo(graph,start);
        //访问起点
        vertexInfo.visit(start);
        //查看是否还有结点需要访问
        while (vertexInfo.getNext() != -1) {
            //依次访问结点
            vertexInfo.visit(vertexInfo.getNext());
        }
        //打印查看生成最短路径集合
        System.out.println(Arrays.toString(vertexInfo.getDistance()));
    }
}

/**
 * 图
 */
class Graph {

    /**
     * 顶点的数据
     */
    private char[] vertex;
    /**
     * 顶点数据邻接矩阵
     */
    private int[][] matrix;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
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
 * 顶点的访问、前缀以及距离信息
 */
class VertexInfo {

    /**
     * 是否被访问，下标同vertex顶点下标
     */
    private boolean[] visit;
    /**
     * 下标顶点与起点的距离，下标同vertex顶点下标
     */
    private int[] distance;
    /**
     * 下标顶点的前一个结点，下标同vertex顶点下标
     */
    private int[] pre;
    /**
     * 图对象
     */
    private Graph graph;

    public VertexInfo(Graph graph, int start) {
        //初始化图对象
        this.graph = graph;
        int vertexCount = graph.getVertex().length;
        //初始化visit
        this.visit = new boolean[vertexCount];
        //初始化distance
        this.distance = new int[vertexCount];
        //填充最大值，为了后面取的最小的路径
        Arrays.fill(this.distance,DijkstraDemo.MAX);
        //自己设置为0
        this.distance[start] = 0;
        //初始化pre
        this.pre = new int[vertexCount];
    }

    /**
     * 访问某个顶点，并根据该顶点更新其周围顶点的信息，包括距离，前缀以及访问等
     * @param index
     */
    public void visit(int index) {
        //设置index为true
        this.visit[index] = true;
        int[][] matrix = graph.getMatrix();
        //遍历当前row对应的col,找邻接的顶点并做处理
        for (int i = 0; i <matrix[index].length; i++) {
            //求到当前顶点i与起点的距离
            //当前顶点i距离起点的距离=前一个顶点index距离起点的距离+当前顶点i距离前一个顶点index的距离
            int curDistance = this.distance[index]+matrix[index][i];
            //如果当前顶点与起点存在连接并且当前顶点i距离起点的距离还更小
            if(!visit[i] && curDistance < this.distance[i]) {
                //设置当前顶点i的距离
                this.distance[i] = curDistance;
                //设置当前顶点i的前缀
                this.pre[i] = index;
            }
        }
    }

    /**
     * 找到下一个需要访问的最小距离的下标
     * @return
     */
    public int getNext() {
        int min = DijkstraDemo.MAX;
        int index = -1;
        for (int i = 0; i < graph.getVertex().length; i++) {
            if(!visit[i] && distance[i]< min) {
               min = distance[i];
               index = i;
            }
        }
        return index;
    }

    public boolean[] getVisit() {
        return visit;
    }

    public void setVisit(boolean[] visit) {
        this.visit = visit;
    }

    public int[] getDistance() {
        return distance;
    }

    public void setDistance(int[] distance) {
        this.distance = distance;
    }

    public int[] getPre() {
        return pre;
    }

    public void setPre(int[] pre) {
        this.pre = pre;
    }
}
