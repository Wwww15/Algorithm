package greedy;

import java.util.*;

/**
 * 算法：贪心算法
 * 1.特点：
 * 1）每一次的求解都是最好或者最优的解
 * 2）结果可能不是最优的解，但是也是接近最优的
 */
public class GreedyDemo {


    public static void main(String[] args) {
        //创建所有地区
        HashSet<String> allArea = new HashSet<>();
        allArea.add("北京");
        allArea.add("上海");
        allArea.add("天津");
        allArea.add("广州");
        allArea.add("深圳");
        allArea.add("成都");
        allArea.add("杭州");
        allArea.add("大连");

        //创建电台，电台包含覆盖的地区
        HashMap<String, HashSet<String>> broadcast = new HashMap<>();

        HashSet<String> areaSet1 = new HashSet<>();
        areaSet1.add("北京");
        areaSet1.add("上海");
        areaSet1.add("天津");

        HashSet<String> areaSet2 = new HashSet<>();
        areaSet2.add("广州");
        areaSet2.add("北京");
        areaSet2.add("深圳");

        HashSet<String> areaSet3 = new HashSet<>();
        areaSet3.add("成都");
        areaSet3.add("上海");
        areaSet3.add("杭州");

        HashSet<String> areaSet4 = new HashSet<>();
        areaSet4.add("上海");
        areaSet4.add("天津");

        HashSet<String> areaSet5 = new HashSet<>();
        areaSet5.add("杭州");
        areaSet5.add("大连");

        //添加到电台Map
        broadcast.put("K1",areaSet1);
        broadcast.put("K2",areaSet2);
        broadcast.put("K3",areaSet3);
        broadcast.put("K4",areaSet4);
        broadcast.put("K5",areaSet5);

        //测试贪心算法
        List<String> greedy = greedy(allArea, broadcast);
        System.out.println(greedy);
    }

    /**
     * 贪心算法
     * 场景：最小的全部地区问题
     * 1.每一轮找出同allArea地区交集最多的电台
     * 2.将最多的电台插入一个返回的集合中
     * 3.移除allArea已经覆盖的地区
     * 4.反复1,2,3直到allArea为空
     *
     * @param allArea   全部地区
     * @param broadcast 所有的电台覆盖的地区
     * @return
     */
    public static List<String> greedy(HashSet<String> allArea, HashMap<String, HashSet<String>> broadcast) {
        //定义存储变量，变量是包含最多范围的地区的电台
        String maxKey = null;
        //定义变量存储当前选择的电台
        List<String> broadcastList = new ArrayList<>();
        //如果当前allArea仍有值，继续遍历
        while (!allArea.isEmpty()) {
            Set<String> keySet = broadcast.keySet();
            //遍历当前的broadcast，找到当前轮包含最多地区的电台
            for (String key : keySet) {
                //获取当前key对应的地区集合
                HashSet<String> areaSet = broadcast.get(key);
                //深度复制，不操作原来的集合
                HashSet<String> tempArea = new HashSet<>(areaSet);
                //判断是否为空
                if (maxKey == null) {
                    maxKey = key;
                } else {
                    //求出当前地区集合同剩余所有地区的交集
                    tempArea.retainAll(allArea);
                    //获取当前maxkey的地区集合与剩余所有地区的交集
                    HashSet<String> maxSet = broadcast.get(maxKey);
                    //深度复制，不操作原来的集合
                    HashSet<String> maxArea = new HashSet<>(maxSet);
                    //这里一样需要求个交集
                    maxArea.retainAll(allArea);
                    //对比，如果当前地区交集大于maxKey对应的交集集合，就重新赋值，否则不变
                    if (tempArea.size() > maxArea.size()) {
                        maxKey = key;
                    }
                }
            }
            //循环完成后
            if (maxKey != null) {
                //添加当前轮的覆盖最多地区的电台key
                broadcastList.add(maxKey);
                //清除allArea中已经被覆盖的地区
                allArea.removeAll(broadcast.get(maxKey));
                //初始化maxKey，给下一轮
                maxKey = null;
            }
        }
        //完成覆盖
        return broadcastList;
    }
}
