package sort;

import utils.ArrayRandomUtils;
import utils.DateUtils;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
//        int arr[] = ArrayRandomUtils.random(100000);
        int arr[] = {101, 34, 119, 1, -1, 90, 123};
        String startDate = DateUtils.now();
        System.out.println(startDate);
        selectSort(arr);
        String endDate = DateUtils.now();
        System.out.println(endDate);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     * 1.外层循环为排序的次数
     * 2.里层循环为当前轮排序需要对比的次数
     * 3.选择排序主要是选择当前轮最大的数或者最小的数，然后同遍历初始位置交换，然后依次往后遍历，交换位置
     *
     * 当前排序为从小到大排序
     * @param arr
     */
    public static void selectSort(int[] arr) {
        //外层循环
        for (int i = 0; i < arr.length-1; i++) {
            //默认当前位置的值最小
            int min = arr[i];
            int minIndex = i;
            for (int j = i+1; j < arr.length-1; j++) {
                if(min>arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            //优化，如果位置变，则交换,反之则不动
            if(minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
