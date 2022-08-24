package sort;

import utils.ArrayRandomUtils;
import utils.DateUtils;


/**
 * 冒泡排序
 * 1.每一轮循环都会找到最大的或者最小的
 * 2.可以用一个标识flog用来优化当前轮是否有进行交换
 */
public class BubbleSort {

    public static void main(String[] args) {
        int arr[] = ArrayRandomUtils.random(100000);
//        int arr[] = {3, 9, -1, 10, 20};
        String startDate = DateUtils.now();
        System.out.println(startDate);
        bubbleSort(arr);
        String endDate = DateUtils.now();
        System.out.println(endDate);
    }

    /**
     * 冒泡排序位置
     * 当前从小到大排序
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        //数组长度为arr.length,只用比较长度-1次
        //外面控制当前排序的次数
        for (int i = 0;i<arr.length-1;i++) {
            //是否有位置交换
            boolean flag = false;
            //里面控制每一轮比较的次数
            for (int j = 0; j < arr.length-1-i; j++) {
                //如果前面大于后面，就交换位置，大的往后冒
                if(arr[j]>arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            //判断一轮中是否有位置交换
            if(!flag) {
                return;
            }
        }
    }
}
