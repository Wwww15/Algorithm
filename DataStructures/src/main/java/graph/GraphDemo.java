package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据结构：图
 * 1.特点
 * 1）不同于线性表的单对单和树的单对多的关系，图支持多对多
 * 2）相关概念：
 * a.顶点(vertex)：就是结点
 * b.边(edge)：两个顶点之间的连接
 * c.路径：从结点到某一个结点的通路，如：A->B
 * d.无向图：顶点之间的连接没有方向的图
 * e.带权的图：边带有权值
 * f.有向图：顶点之间的连接带有方向的图
 * 3）邻接矩阵：表示图关系的二维数组，n个顶点的图就是row=n，col=n构成的二维数组，二维数组的值就表示对应的结点是否有连接，如：0表示无连接，1表示有连接，自身可以用0也可以用1，看情况
 * 4）邻接表：相对于邻接矩阵，邻接表更能节省空间，邻接表只表示存在的边，对于不存在边，邻接表不关注，其结构类似于数组+链表
 */
public class GraphDemo {


    public static void main(String[] args) {
        //设置顶点个数
        int vertexCount = 5;
        //设置顶点的值
        String[] vertexArr = {"A","B","C","D","E"};
        //初始化图
        Graph graph = new Graph(vertexCount);
        //添加顶点
        for (int i = 0; i < vertexArr.length; i++) {
            graph.insertVertex(vertexArr[i]);
        }
        //添加边
        graph.addEdge(0,1,1);
        graph.addEdge(0,2,1);
        graph.addEdge(1,2,1);
        graph.addEdge(1,3,1);
        graph.addEdge(1,4,1);
        //遍历图
        graph.showGraph();

        //深度优先遍历
        graph.deptFirstSearch();

        //广度优先遍历
//        graph.broadFirstSearch();
    }

}

/**
 * 图
 */
class Graph {

    /**
     * 边的个数
     */
    private int edgeCount;
    /**
     * 顶点的集合
     * 1.这里默认A-0,B-1,C-2,D-3,E-4
     */
    private List<String> vertexList;
    /**
     * 顶点之间的关系
     */
    private int[][] graphArr;
    /**
     * 是否访问标志的存储数组，按照下标对应标志位
     */
    private boolean[] visit;

    /**
     * 初始化图
     *
     * @param vertexCount 顶点的个数
     */
    public Graph(int vertexCount) {
        this.vertexList = new ArrayList<>(vertexCount);
        this.graphArr = new int[vertexCount][vertexCount];
    }

    /**
     * 插入顶点
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入边
     * @param vertex1 顶点1，这里用下标表示
     * @param vertex2 顶点2，这里用下标表示
     * @return 顶点之间的连接，这里用0和1表示
     */
    public void addEdge(int vertex1,int vertex2,int val) {
        //这里无向图需要双向绑定
        graphArr[vertex1][vertex2] = val;
        graphArr[vertex2][vertex1] = val;
        edgeCount++;
    }

    /**
     * 遍历图
     */
    public void showGraph() {
        for(int[] row: graphArr) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * 深度优先遍历
     * 1.定一个头顶点，从头顶点开始遍历
     * 2.将当前遍历的顶点的访问标记置为已访问
     * 3.遍历当前顶点的第一个邻接顶点，该邻接顶点必须与当前顶点有连接
     *  1）当前邻接顶点存在
     *     a.没被访问，递归当前邻接顶点，重复2,3步骤
     *     b.已被访问，将遍历的邻接顶点置为当前顶点的下一个邻接顶点
     *  2）当前邻接顶点不存在，直接第4步
     * 4.当前顶点遍历完后，继续深度遍历下一个顶点
     *
     * 总结：从一个结点开始遍历，走完一个分支后，再走另一个分支
     */
    public void deptFirstSearch() {
        visit = new boolean[vertexCount()];
        //这里是否需要遍历，有待商榷 TODO
        //如果这里都是有连接的顶点，这里不需要循环
//        for (int i = 0; i < vertexCount(); i++) {
//            if(!visit[i]) {
                doDeptFirstSearch(0);
//            }
//        }
    }

    /**
     * 深度优先遍历实际位置
     * @param rowIndex
     */
    public void doDeptFirstSearch(int rowIndex) {
        //打印当前结点
        System.out.print(vertexVal(rowIndex)+"->");
        //将当前结点设置为已访问
        visit[rowIndex] = true;
        //找到有连接的邻接结点
        int neighborIndex = getNextNeighborNode(rowIndex, 0);
        //已找到
        while(neighborIndex != -1) {
            //是否被访问
            if(!visit[neighborIndex]) {
                //递归遍历
                doDeptFirstSearch(neighborIndex);
            }
            //已经被访问，继续遍历当前结点的下一个邻接结点
            neighborIndex = getNextNeighborNode(rowIndex,neighborIndex+1);
        }
    }

    /**
     * 广度优先遍历
     * 总结：按照层级一层层的往下遍历
     *
     */
    public void broadFirstSearch() {
        visit = new boolean[vertexCount()];
        doBroadFirstSearch(0);
    }

    /**
     * 广度优先遍历真正的实现
     */
    public void doBroadFirstSearch(int rowIndex) {
        //存储当前遍历到的顶点
        LinkedList<Integer> temp = new LinkedList<Integer>();
        //添加初始化结点到temp
        temp.addLast(rowIndex);
        //当前的temp还有未往下遍历的结点
        //打印当前结点
        System.out.print(vertexVal(rowIndex)+"->");
        //当前结点置为已访问
        visit[rowIndex] = true;
        while(!temp.isEmpty()) {
            int curIndex = temp.removeFirst();
            //广度遍历，获取当前结点的下一个邻接结点
            int nextNode = getNextNeighborNode(curIndex, 0);
            //下一个邻接结点存在
            while (nextNode != -1) {
                //未被访问
                if(!visit[nextNode]) {
                    //打印当前结点
                    System.out.print(vertexVal(nextNode)+"->");
                    //当前结点置为已访问
                    visit[nextNode] = true;
                    //将当前结点添加到temp
                    temp.addLast(nextNode);
                }
                //已被访问
                nextNode = getNextNeighborNode(curIndex,nextNode+1);
            }
        }
    }

    /**
     * 根据初始下标找到最近的邻接结点，这里邻接结点必须与当前结点有连接
     */
    public int getNextNeighborNode(int rowIndex,int startCol) {
        for (int i = startCol; i < vertexCount(); i++) {
            if(graphArr[rowIndex][i] == 1) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 对应的顶点个数
     * @return
     */
    public int vertexCount() {
        return vertexList.size();
    }

    /**
     * 边的个数
     * @return
     */
    public int edgeCount(){
        return edgeCount;
    }

    /**
     * 顶点下标对应的顶点值
     * @return
     */
    public String vertexVal(int index) {
        return vertexList.get(index);
    }

    /**
     * 顶点之间对应的连接关系
     * @return
     */
    public int edge(int vertex1,int vertex2) {
        return graphArr[vertex1][vertex2];
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

    public List<String> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<String> vertexList) {
        this.vertexList = vertexList;
    }

    public int[][] getGraphArr() {
        return graphArr;
    }

    public void setGraphArr(int[][] graphArr) {
        this.graphArr = graphArr;
    }
}
