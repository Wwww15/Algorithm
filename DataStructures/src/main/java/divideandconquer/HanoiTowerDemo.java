package divideandconquer;

/**
 * 算法：汉诺塔
 */
public class HanoiTowerDemo {

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower();
        hanoiTower.hanoiTower(3,'A','B','C');
    }

}

/**
 * 汉诺塔算法：多个盘子初始的时候按照大小顺序放在A这个柱子上，现在需要通过A、B、C三个柱子将盘子移动到C柱子上，需要注意的是，大盘子不能放在小盘子上
 * 分析：
 * 1.如果只有一个盘子，A->C
 * 2.如果n>=2，则
 *  1）首先将最后一个盘子前面的所有的都看做一个盘子，则(n-1)个盘子需要移动到B，A->B
 *  2）最后一个盘子移动到C，A->C
 *  3）然后将B柱子上的盘子移动到C，B->C
 */
class HanoiTower{

    /**
     * 汉诺塔实现
     * @param count 盘子数量
     * @param a 初始柱子
     * @param b 辅助柱子
     * @param c 目标柱子
     */
    public void hanoiTower(int count,char a,char b,char c) {
        if(count<1) {
            return;
        }
        if(count ==1) {
            System.out.println("第"+count+"个盘子，从"+a+"到"+c);
        }else {
            //n-1个盘子从A->B
            hanoiTower(count-1,a,c,b);
            //第n个盘子从A->C
            System.out.println("第"+count+"个盘子，从"+a+"到"+c);
            //n-1个盘子从B->C
            hanoiTower(count-1,b,a,c);
        }
    }
}
