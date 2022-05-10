package sort;

import java.util.Arrays;

public class QuickSort {


    public static void main(String[] args) {
        int sortArr[] = {199,0,-99,-100,1,-99,-500,9999,1231,5,1,-99};
        quickSort(sortArr,0,sortArr.length-1);
        System.out.println(Arrays.toString(sortArr));
    }

    public static void quickSort(int[] arr,int left,int right) {
        int l = left;
        int r = right;
        //1.l从左往右遍历
        //2.r从右往左遍历
        //获取中间值
        int pivot = arr[(left+right)/2];
        System.out.println("中间数："+pivot);
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
            System.out.println("交换前的数组："+Arrays.toString(arr));
            temp = arr[l];
            arr[l] =  arr[r];
            arr[r] = temp;
            System.out.println("交换后的数组："+Arrays.toString(arr));
            System.out.println("当前的l："+l);
            System.out.println("当前的r："+r);

            if(arr[l] == pivot)
            {
                r--;
            }

            if(arr[r] == pivot)
            {
                l++;
            }

        }
    }
}
