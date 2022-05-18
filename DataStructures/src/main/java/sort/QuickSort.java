package sort;

import java.util.Arrays;

//排序算法：快速排序
//1.找准一个基准数，并对数组进行遍历（这里选择中间index的值）
//2.从左往右和从右往左分别遍历，等到双方都取到值后，交换（注意等于中间值的情况）
//3.利用递归再分别将左边和右边进行排序处理，方式同上，直到left>r,right>l
public class QuickSort {

    public static void main(String[] args) {
        int sortArr[] = {199,0,-99,-100,1,-99,-500,9999,1231,5,1,-99};
        quickSort(sortArr,0,sortArr.length-1);
        System.out.println(Arrays.toString(sortArr));
    }

    public static void quickSort(int[] arr,int left,int right) {
        //定义两个变量用于表示当前的左起点和右起点
        int l = left;
        int r = right;
        //1.l从左往右遍历
        //2.r从右往左遍历
        //获取中间值
        int pivot = arr[(left+right)/2];
        int temp = 0;
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

            //避免等于pivot的情况下死循环
            if(arr[l] == pivot)
            {
                //避免两个数同时移位导致少交换
                r--;
            }

            //避免等于pivot的情况下死循环
            if(arr[r] == pivot)
            {
                //避免两个数同时移位导致少交换
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
