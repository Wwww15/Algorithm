package horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

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
        int[][] horseChessBoard = horseChessBoard(6, 6, new Point(5, 2));
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
     * 1.初始化一个chessBoard棋盘
     * 2.记录当前的访问记录（也就是走过了）visit
     * 3.定义一个起点，标记当前点已被访问，记录当前的步数，然后找到该点能继续往下走的，全部放在list里面
     * 4.优化list（排序），为了减少回溯次数，然后再遍历list，最多8个，取出一个point，如果当前point没被访问，就继续往下走，setpNum+1，使用深度优先遍历
     * 5.最后遍历完list或者没有能走通的，则回溯，判断当前是否已经走完并且已经结束（这里要有个结束标记，因为回溯的时候stepNum会变化，但是结束标记作为全局变量不会，这样就退出了）
     * 6.如果已经走完，就不操作了，没有走完，重置当前的访问标记为false，重置当前的棋盘为0
     * @param row        棋盘有几行
     * @param column     棋盘有几列
     * @param startPoint 马初始位置，行和列都从0开始
     * @return
     */
    public static int[][] horseChessBoard(int row, int column, Point startPoint) {
//        //初始化棋盘
//        Point[][] pointArr = buildChess(row, column);
        //初始化访问数组
        boolean[][] visit = new boolean[row][column];
        //初始化步骤数组
        int[][] chessBoard = new int[row][column];
        //定义一个第几步
        int stepNum = 1;
        //真正开始执行马踏棋盘算法
        doHorseChessBoard(row, column, visit, chessBoard, stepNum, startPoint);
        return chessBoard;
    }


    /**
     * 马踏棋盘算法实践——核心执行位置
     *
     * @param visit   是否访问数组
     * @param chessBoard    棋盘，保存步骤
     * @param stepNum 步数
     * @param point   起点
     */
    private static void doHorseChessBoard(int row, int column, boolean[][] visit, int[][] chessBoard, int stepNum, Point point) {
        //设置当前位置已访问
        visit[point.y][point.x] = true;
        //设置当前步骤的数组
        chessBoard[point.y][point.x] = stepNum;
        //初始化集合
        ArrayList<Point> pointList = nextList(row, column, point);
        //优化，对集合进行排序
        pointList.sort((o1, o2) -> {
           //获取到o1的集合
            ArrayList<Point> o1List = nextList(row, column, o1);
            //获取到o2的集合
            ArrayList<Point> o2List = nextList(row, column, o2);
            //判断
            if(o1List.size()<o2List.size()) {
                return -1;
            }else if(o1List.size()>o2List.size()) {
                return 1;
            }else {
                return 0;
            }
        });
        //判断是否有可走的位置
        while (!pointList.isEmpty()) {
            Point nextPoint = pointList.remove(0);
            //判断是否被访问
           if(!visit[nextPoint.y][nextPoint.x]) {
               //递归往下走
               doHorseChessBoard(row,column,visit,chessBoard,stepNum+1,nextPoint);
           }
        }
        //判断是否走完，finish标记为了回溯的时候没法判断是否走完
        if(stepNum < row*column && !finish) {
            //设置当前位置已访问
            visit[point.y][point.x] = false;
            //设置当前步骤的数组
            chessBoard[point.y][point.x] = 0;
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


