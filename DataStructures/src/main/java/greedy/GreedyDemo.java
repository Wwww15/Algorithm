package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 算法：贪心算法
 * 1.特点：
 *  1）每一次的求解都是最好或者最优的解
 *  2）结果可能不是最优的解，但是也是接近最优的
 */
public class GreedyDemo {


    public static void main(String[] args) {

    }

    /**
     * 贪心算法
     * 场景：最小的全部地区问题
     * 1.每一轮找出同allArea地区交集最多的电台
     * 2.将最多的电台插入一个返回的集合中
     * 3.移除allArea已经覆盖的地区
     * 4.反复1,2,3直到allArea为空
     * @param allArea 全部地区
     * @param broadcast 所有的电台覆盖的地区
     * @return
     */
    public static List greedy(HashSet<String> allArea, HashMap<String,HashSet> broadcast){
        return new ArrayList();
    }
}
