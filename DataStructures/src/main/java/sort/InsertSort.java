package sort;

import utils.DateUtils;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
//        int arr[] = ArrayRandomUtils.random(100000);
        int arr[] = {101, 34, 119, 1, -1, 89};
        String startDate = DateUtils.now();
        System.out.println(startDate);
        insertSort(arr);
        String endDate = DateUtils.now();
        System.out.println(endDate);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序
     * 1.外层循环表示当前排序需要的插入次数
     * 2.里层循环表示当前轮查找插入位置的次数
     * 3.插入排序是将待排序数组看成两段，一段是有序的，一段是无序的，从左往右排序，
     *   每次排序都从右边的无序数组中取出一个数，在左边合适的位置插入，形成一个新的有序数组
     *
     * 这里从小到大排序
     */
    public static void insertSort(int[] arr) {
        //外层循环,这里第一个数不需要排序
        for (int i = 1; i < arr.length; i++) {
            //前面待插入的位置
            int insertIndex = i-1;
            //待插入的树
            int insertVal = arr[i];
            //比较当前下标是否越界并且待插入的值是否是合适的位置
            while (insertIndex>=0 && insertVal < arr[insertIndex]) {
                //还未找到插入位置，需要将当前位置的值往后移，因为i位置值已经被取出
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            //循环完成，这里已经找到了合适的位置
            //判断当前是否需要插入
            if(insertIndex+1 != i) {
                arr[insertIndex+1] = insertVal;
            }
        }
    }
}
