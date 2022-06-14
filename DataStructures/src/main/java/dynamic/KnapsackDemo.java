package dynamic;

import java.util.Arrays;

/**
 * 算法：动态规划-背包问题
 * 1.特点：
 * 1）类似分治算法，相同点都是将大的问题分成小的问题解决，小的问题，不同点是，分治算法的每个问题之间没有影响，相互独立，但是动态规划算法，每个问题都需要依赖于上一个问题的结果
 * 2）大问题分解->小问题
 * 3）可以通过填表的方式求得最佳解
 */
public class KnapsackDemo {

    public static void main(String[] args) {
        int v[] = {1000,3000,2500,2000,3500};
        int w[] = {1,4,3,2,5};
        int maxW = 6;
        knapsack(w,v,maxW);
    }

    /**
     * 背包问题解决
     * 1.利用二维数组 val 存储当前重量为j下，前i个物品的最大价值(i：物品,j：重量)
     * 2.新增一行和一列，第一列和第一行的值：val[0][j] = val[i][0] = 0
     * 3.判断当前 w[i-1] 的值与 j 的重量比较（i-1因为同重量和值比例，二维数组多添加第一列和第一行）
     *  1）如果 w[i-1]>j,表示当前物品重量大于最大重量，则不需要考虑当前物品价值， val[i][j] = val[i-1][j]
     *  2) 如果小于或者等于，则有可能分成多个物品组合，则判断之前的价值和当前的价值:
     *      a. val[i-1][j] 大，则 val[i][j] = val[i-1][j]
     *      b. v[i-1]+val[j-w[i-1]] 大,则 val[i][j] = v[i-1]+val[i-1][j-w[i-1]]
     * 4.额外新增一个二维数组 path ，存储当前放入背包的物品Index，方便后面能快速知道最优解的物品有哪些
     * 5.倒叙遍历 path ，取出最优解下放入的物品
     * @param w    重量集合，按照物品的顺序
     * @param v    价值集合。排序同上
     * @param maxW 最大的重量
     */
    public static void knapsack(int w[], int[] v, int maxW) {
        //定义val的行
        int row = v.length+1;
        //定义val的列,这里注意，是根据最大重量来定义列的值
        int col = maxW+1;
        //初始化val
        int[][] val = new int[row][col];
        //定义一个二维数组存储当前新增的物品index，并根据这个求得最优解里面的物品有哪些
        int path[][] = new int[row][col];
        //新增一行值为0的行，为了以后自定义初始化值
        for (int i = 0; i < val.length ; i++) {
            val[i][0] = 0;
        }
        //新增一列值为0的列，为了以后自定义初始化值
        for (int j = 0; j < val[0].length ; j++) {
            val[0][j] = 0;
        }
        //开始利用动态规划生成完整的二维数组，求得每个j下，前i个物品的最大价值
        //这里要从1开始遍历，因为二维数组多一行一列，但是重量和价值并没有
        for (int i = 1; i <val.length ; i++) {
            for (int j = 1; j <val[i].length ; j++) {
                //开始判断当前j下得最大价值
                if(w[i-1]>j) {
                    val[i][j] = val[i-1][j];
                }else {
//                    val[i][j] = Math.max(val[i-1][j],v[i-1]+val[i-1][j-w[i-1]]);
                    //肯定有v才会有物品的加入,这里根据比较的不同，最优解可能不同，如果价值在 a+b =c 下
                    if(val[i-1][j] >= v[i-1]+val[i-1][j-w[i-1]]) {
                        val[i][j] = val[i-1][j];
                    }
                    else {
                        val[i][j] = v[i-1]+val[i-1][j-w[i-1]];
                        //当前加入了物品
                        path[i][j] = 1;
                    }
                }
            }
        }
        //查看当前的价值生成val
        for (int[] rowArr:val) {
            System.out.println(Arrays.toString(rowArr));
        }
        //找出最优解
        //最优解肯定是当前path二维数组里面的最后一个值
        int pathI = path.length-1;
        int pathJ = path[0].length-1;
        //倒叙遍历，分解重量
        while (pathI>0 && pathJ >0) {
            if(path[pathI][pathJ] == 1){
                System.out.println("加入了商品："+pathI);
                pathJ -= w[pathI-1];
            }
            pathI --;
        }
    }
}
