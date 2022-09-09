package prim;

/**
 * 算法：普里姆算法
 * 1.特点：
 * 1）解决村庄修路的最小生成树
 * 2）遍历当前的邻接矩阵，通过找到每一轮最小的weight（也就是最短路径），路径结束顶点需要没有被访问
 */
public class PrimDemo {

    public static void main(String[] args) {
        //初始化顶点
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int[][] matrix=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};
        Graph graph = new Graph(data, matrix);
        graph.prim(0);
    }
}

/**
 * 图
 */
class Graph {

    /**
     * 顶点对应的数组
     */
    char[] vertexArr;
    /**
     * 顶点之间的关系
     */
    int[][] matrix;

    public Graph(char[] vertexArr, int[][] matrix) {
        this.vertexArr = vertexArr;
        this.matrix = matrix;
    }

    /**
     * 普里姆算法实践
     * 1.从某一个顶点开始，找到该顶点与连通的未被访问顶点之间的最小路径
     * 2.然后将该最小路径顶点标记为已访问
     * 3.然后再继续在所有已经被访问的顶点里面，寻找与之连通的未被访问的顶点之间的最小路径
     * 4.就这样，重复循环顶点n-1次，最小生成树找到
     * @param  start 设置算法的起点，也就是第一个被访问的顶点
     */
    public void prim(int start) {
        //定义一个存储顶点是否被访问的数组，这里下标默认对应顶点数组下标
        boolean[] visit = new boolean[vertexArr.length];
        //初始化第一个顶点，也就是起点
        visit[start] = true;
        //当前遍历是为了循环几次找到需要的路径,n个顶点，需要n-1条路径
        for (int k = 0; k <vertexArr.length-1; k++) {
            //初始化最小的路径
            int minWeight = 10000;
            //记录当前轮的起点
            int startIndex = -1;
            //记录当前轮的终点
            int endIndex = -1;
            //遍历行，也可看成已被访问的起点
            for (int i = 0; i < matrix.length; i++) {
                //遍历列，也可看成未被访问的终点
                for (int j = 0; j < matrix[i].length; j++) {
                    //如果起点已经被访问，并且终点未被访问
                    if(visit[i] && !visit[j]) {
                        //如果比当前轮的最小路径还小
                        if(minWeight>matrix[i][j]) {
                            //替换当前minWeight
                            minWeight = matrix[i][j];
                            //替换当前轮的起点
                            startIndex = i;
                            //替换当前轮的终点
                            endIndex = j;
                        }
                    }
                }
            }
            System.out.println(vertexArr[startIndex]+"->"+vertexArr[endIndex]+"："+minWeight);
            //设置终点被访问
            if(endIndex != -1) {
                visit[endIndex] = true;
            }
        }
    }

    public char[] getVertexArr() {
        return vertexArr;
    }

    public void setVertexArr(char[] vertexArr) {
        this.vertexArr = vertexArr;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
