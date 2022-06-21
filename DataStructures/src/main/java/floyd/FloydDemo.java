package floyd;

import java.util.Arrays;

/**
 * 算法：弗洛伊德算法——最短路径
 * 1.特点：
 * 1)弗洛伊德算法是求出所有顶点的最短路径，同迪杰斯特拉求某一个顶点的最短路径不同
 * 2)主要是判断通过中间结点k连接起点i和终点j的距离，与直接连接i和j的距离的大小
 * 3)循环是三层，最外层是中间结点循环，中间是起点循环，最里面是终点循环
 * 4)distance[][]记录当前起点与终点的距离，relate[][]记录当前的连接点
 */
public class FloydDemo {

    public static void main(String[] args) {
        //创建顶点
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建图信息
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        //执行算法
        int[][] floyd = floyd(vertex, matrix);

        //打印
        for (int i = 0; i < floyd.length; i++) {
            System.out.println(Arrays.toString(floyd[i]));
        }
    }

    /**
     * 弗洛伊德算法实践
     * @return
     */
    public static int[][] floyd(char[] vertex,int[][] matrix){
        //构建图
        Graph graph = new Graph(vertex,matrix);
        //获取当前通过中间结点连接的距离
        int[][] distance = graph.getMatrix();
        int[][] relate = graph.getRelate();
        //三层循环
        //该循环为中间结点
        for (int  k = 0;  k< vertex.length ; k++) {
            //该循环为起点
            for (int i = 0; i < matrix.length ; i++) {
                //该循环为终点
                for (int j = 0; j < matrix[i].length; j++) {
                    //直接相连的距离
                    int direct = distance[i][j];
                    //间接相连的距离
                    int indirect = distance[i][k]+distance[k][j];
                    //判断当前构成的距离，直接大于间接，则更改距离值
                    if(direct>indirect) {
                        //更新距离值
                        distance[i][j] = indirect;
                        //更改关联结点
                        relate[i][j] = relate[k][j];
                    }
                }
            }
        }
        return distance;
    }
}

/**
 * 图
 */
class Graph {

    /**
     * 顶点数组
     */
    private char[] vertex;
    /**
     * 图的数据，邻接矩阵
     */
    private int[][] matrix;

    /**
     * 定义连接点
     */
    private int[][] relate;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        this.relate = new int[vertex.length][vertex.length];
        //初始化连接点，默认起点就是连接点，row为起点，column为终点
        for (int i = 0; i < relate.length; i++) {
            Arrays.fill(relate[i],i);
        }
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

    public int[][] getRelate() {
        return relate;
    }

    public void setRelate(int[][] relate) {
        this.relate = relate;
    }
}
