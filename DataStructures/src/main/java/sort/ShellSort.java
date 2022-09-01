package sort;

import utils.ArrayRandomUtils;
import utils.DateUtils;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int arr[] = ArrayRandomUtils.random(100000000);
//        int arr[] = {10,8,9,1,7,2,3,5,4,6,0};
        String startDate = DateUtils.now();
        System.out.println(startDate);
        shellSort(arr);
        String endDate = DateUtils.now();
        System.out.println(endDate);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序
     * 1.希尔排序是插入排序的改进算法，也叫缩小增量排序，其主要是通过增量将未排序的数组拆分成组，在每个分组内进行排序，然后再根据增量分组排序，直到增量为1
     */
    public static void shellSort(int[] arr) {
        //外层为缩小增量
        for (int gap = arr.length/2; gap >= 1; gap /= 2) {
            //外层为gap分组，这里遍历应该是从下标第二个开始
            for (int i = gap; i < arr.length; i++) {
                //分组内排序,采用插入排序
                gapInsertSort(arr,i,gap);
                //采用冒泡排序
//                gapBubbleSort(arr,i,gap);
            }
        }
    }

    /**
     * 增量之间的插入排序
     *
     * 从小到大
     * @param arr
     * @param index 插入值的下标
     * @param gap 增量
     */
    public static void gapInsertSort(int[] arr,int index,int gap) {
        //待插入的下标
        int insertIndex = index - gap;
        //待插入的值
        int insertVal = arr[index];
        //比较当前是否越界并且当前是否是插入位置
        while (insertIndex >=0 && insertVal<arr[insertIndex]) {
            arr[insertIndex+gap] = arr[insertIndex];
            insertIndex -= gap;
        }
        if(insertIndex + gap != index) {
            arr[insertIndex+gap] = insertVal;
        }
    }

    /**
     * 增量之间的冒泡排序
     * 1.这里冒泡排序要依照插入排序的规则，最小的在最左边，所以往左边冒泡选出最小的
     * @param arr
     * @param index
     * @param gap
     */
    public static void gapBubbleSort(int[] arr,int index,int gap) {
        //每一轮需要对比的值的次数
        for (int i = index-gap; i >=0; i -= gap) {
            if(arr[i+gap]<arr[i]) {
                int temp = arr[i+gap];
                arr[i+gap] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
