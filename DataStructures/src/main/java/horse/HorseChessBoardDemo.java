package horse;

import java.awt.*;
import java.util.ArrayList;

/**
 * 算法：马踏棋盘算法（骑士周游算法）
 * 1.特点：
 * 1）马走日字，一共有做多有8钟走法，一一列出（查找的时候判断是否超出边界），然后取出一个往下递归
 * 2）利用递归的方式实现深度遍历，尝试当前走法是否能走完
 * 3）利用回溯解决当前不能走完的情况，处理访问标记，棋盘恢复
 * 2.优化
 * 1）将当前第一步查找走法的时候，按照从小到大排序后，再一个个取出，减少回溯的次数
 */
public class HorseChessBoardDemo {

    private static boolean finish = false;

    public static void main(String[] args) {
        //执行马踏棋盘算法
        int[][] horseChessBoard = horseChessBoard(8, 8, new Point(0, 0));
        //遍历步骤结果
        for (int i = 0; i < horseChessBoard.length; i++) {
            for (int j = 0; j < horseChessBoard[i].length; j++) {
                System.out.printf("%d\t",horseChessBoard[i][j]);
            }
            System.out.println("");
        }
    }

    /**
     * 马踏棋盘算法实践——总的方法
     *
     * @param row        棋盘有几行
     * @param column     棋盘有几列
     * @param startPoint 马初始位置
     * @return
     */
    public static int[][] horseChessBoard(int row, int column, Point startPoint) {
//        //初始化棋盘
//        Point[][] pointArr = buildChess(row, column);
        //初始化访问数组
        boolean[][] visit = new boolean[row][column];
        //初始化步骤数组
        int[][] step = new int[row][column];
        //定义一个第几步
        int stepNum = 1;
        //真正开始执行马踏棋盘算法
        doHorseChessBoard(row, column, visit, step, stepNum, startPoint);
        return step;
    }


    /**
     * 马踏棋盘算法实践——核心执行位置
     *
     * @param visit   是否访问数组
     * @param step    步骤数组
     * @param stepNum 步数
     * @param point   起点
     */
    private static void doHorseChessBoard(int row, int column, boolean[][] visit, int[][] step, int stepNum, Point point) {
        //设置当前位置已访问
        visit[point.y][point.x] = true;
        //设置当前步骤的数组
        step[point.y][point.x] = stepNum;
        //初始化集合
        ArrayList<Point> pointList = nextList(row, column, point);
        //判断是否有可走的位置
        while (!pointList.isEmpty()) {
            Point nextPoint = pointList.remove(0);
            //判断是否被访问
           if(!visit[nextPoint.y][nextPoint.x]) {
               //递归往下走
               doHorseChessBoard(row,column,visit,step,stepNum+1,nextPoint);
           }
        }
        //判断是否走完
        if(stepNum < row*column && !finish) {
            //设置当前位置已访问
            visit[point.y][point.x] = false;
            //设置当前步骤的数组
            step[point.y][point.x] = 0;
        } else {
            finish = true;
        }
    }

    /**
     * 获取当前可走的集合
     * 1.总共有8个位置
     * 2.判断边界
     *
     * @param point
     * @return
     */
    private static ArrayList<Point> nextList(int row, int column, Point point) {
        ArrayList<Point> pointList = new ArrayList<>();
        //第一种
        if (point.x - 2 >= 0 && point.y - 1 >= 0) {
            pointList.add(new Point(point.x - 2, point.y - 1));
        }
        //第二种
        if (point.x - 1 >= 0 && point.y - 2 >= 0) {
            pointList.add(new Point(point.x - 1, point.y - 2));
        }
        //第三种
        if (point.x + 1 < column && point.y - 2 >= 0) {
            pointList.add(new Point(point.x + 1, point.y - 2));
        }
        //第四种
        if (point.x + 2 < column && point.y - 1 >= 0) {
            pointList.add(new Point(point.x + 2, point.y - 1));
        }
        //第五种
        if (point.x + 2 < column && point.y + 1 < row) {
            pointList.add(new Point(point.x + 2, point.y + 1));
        }
        //第六种
        if (point.x + 1 < column && point.y + 2 < row) {
            pointList.add(new Point(point.x +1, point.y+2));
        }
        //第七种
        if (point.x - 1 >= 0 && point.y + 2 < row) {
            pointList.add(new Point(point.x - 1, point.y + 2));
        }
        //第八种
        if (point.x - 2 >= 0 && point.y + 1 < row) {
            pointList.add(new Point(point.x - 2, point.y + 1));
        }
        return pointList;
    }


    /**
     * 初始化棋盘
     *
     * @param row
     * @param column
     */
    public static Point[][] buildChess(int row, int column) {
        Point[][] pointArr = new Point[row][column];
        //初始化一个点构成的数组
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //这里column为x，row为y
                pointArr[i][j] = new Point(j, i);
            }
        }
        return pointArr;
    }


}


