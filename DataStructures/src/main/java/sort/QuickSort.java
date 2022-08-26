package sort;

import utils.ArrayRandomUtils;
import utils.DateUtils;

import java.util.Arrays;

/**排序算法：快速排序
 * 1.找准一个基准数，并对数组进行遍历（这里选择中间index的值）
 * 2.从左往右和从右往左分别遍历，等到双方都取到值后，交换（注意等于中间值的情况）
 * 3.利用递归再分别将左边和右边进行排序处理，方式同上，直到left>r,right>l
 * 4.对冒泡排序的一种改进
 */
public class QuickSort {

    public static void main(String[] args) {
                int arr[] = ArrayRandomUtils.random(100000000);
//        int arr[] = {199,0,-99,-100,1,-99,-500,9999,1231,5,1,-99};
        String startDate = DateUtils.now();
        System.out.println(startDate);
        quickSort(arr,0,arr.length-1);
        String endDate = DateUtils.now();
        System.out.println(endDate);
    }

    /**
     * 快速排序
     * @param arr 数组
     * @param left 左边下标
     * @param right 右边下标
     */
    public static void quickSort(int[] arr,int left,int right) {
        //定义两个变量用于表示当前的左起点和右起点
        int l = left;
        int r = right;
        //1.l从左往右遍历
        //2.r从右往左遍历
        //获取中间值
        int pivot = arr[(left+right)/2];
        int temp = 0;
        //这里表示两边的遍历没有重叠，某一边遍历完成了
        while (l<r) {
            //获取左边的大于pivot的值
            while(arr[l]<pivot) {
                l++;
            }
            //获取右边小于pivot的值
            while (arr[r]>pivot) {
                r--;
            }
            //当前的左右分段已经完成
            if(l>=r) {
                break;
            }

            //找到了两边需要换当前的值
            temp = arr[l];
            arr[l] =  arr[r];
            arr[r] = temp;

            //避免两边都是等于pivot的情况下死循环
            if(arr[l] == pivot)
            {
                //移位避免死循环
                //TODO 但是为什么不移当前l的位置
                r--;
            }

            //避免两边都是等于pivot的情况下死循环
            if(arr[r] == pivot)
            {
                //移位避免死循环
                //TODO 但是为什么不移当前r的位置
                l++;
            }

        }

        //防止到最后r = 1 = 某个值的时候造成死循环
        if(r == l) {
            r--;
            l++;
        }

        //向左继续排序
        if(left< r) {
            quickSort(arr,left,r);
        }

        //向右排序
        if(right>l) {
            quickSort(arr,l,right);
        }
    }
}
