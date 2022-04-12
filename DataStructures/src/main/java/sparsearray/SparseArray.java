package sparsearray;

/**
 * 围棋存盘，稀疏数组
 * 特点；
 *  1.第一行表示几行几列，有多少个不同的值
 *  2.缩小数据
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组
        //0：表示没有棋子
        //1：表示黑子
        //2：表示蓝子
        int chessArr[][] = new int[11][11];
        //放入棋子
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][4] = 2;
        //查看棋盘
        System.out.println("当前棋盘：");
        for (int i[] : chessArr) {
            for (int j = 0; j < i.length; j++) {
                System.out.print(i[j]+"\t");
            }
            System.out.println("\n");
        }

        //存盘
        //将二维数据转化为稀疏数组
        //1.获取稀疏数组的大小
        int sum = 0;
        for (int i[] : chessArr) {
            for (int j = 0; j < i.length; j++) {
               if(i[j] !=0 ) {
                   sum++;
               }
            }
        }
        //2.创建稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;
        int row = 1;
        //3.存值
        for (int i=0;i<chessArr.length;i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if(chessArr[i][j] !=0 ) {
                    sparseArr[row][0] = i;
                    sparseArr[row][1] = j;
                    sparseArr[row][2] = chessArr[i][j];
                    row++;
                }
            }
        }
        //4.查看压缩后的稀疏数组
        System.out.println("存盘后：");
        for (int i[]:sparseArr) {
            for (int j = 0; j <  i.length; j++) {
                System.out.print(i[j]+"\t");
            }
            System.out.println("\n");
        }

        //复盘
        //将稀疏数组恢复成二维数组
        //1.先获取稀疏数组第一行数据，生成二维数组
        int chessBackArr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.遍历稀疏数组赋值给二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessBackArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //3.打印生成后的二维数据
        System.out.println("复盘后的二维数组：");
        for (int i = 0; i < chessBackArr.length; i++) {
            for (int j = 0; j < chessBackArr[i].length; j++) {
                System.out.print(chessBackArr[i][j]+"\t");
            }
            System.out.println("\n");
        }
    }
}
