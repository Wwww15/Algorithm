package sort;

import java.util.Arrays;

//排序算法：归并排序
//分而治之
//利用递归的方式，将数组分段，然后再对比排序
public class MergeSort {

    public static void main(String[] args) {
        int sortArr[] = {8,4,5,7,1,3,6,2};
        int temp[] = new int[sortArr.length];
        mergeSort(sortArr,0,sortArr.length-1,temp);
        System.out.println(Arrays.toString(sortArr));
    }

    /**
     * 分段，并且合并排序
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int arr[],int left,int right,int temp[]) {
        if(left<right) {
            int mid = (left+right)/2;
            //向左边递归，分段并合并排序
            mergeSort(arr,left,mid,temp);
            //向右边递归，分段并合并排序
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }


    /**
     * 合并当前的数组
     * @param arr 当前的数组
     * @param left 最右边
     * @param mid 中间分段处
     * @param right 最右边
     * @param temp 临时存储（排序后的数组）
     */
    public static void merge(int arr[],int left,int mid,int right,int temp[]) {
        //临时left，为了方便后面使用
        int l = left;
        //当前temp的index变量
        int tempIndex= 0;
        //右边的起始index
        int rightStartIndex = mid +1;
        //都从左往右遍历递增，限制遍历次数
        while(l<=mid && rightStartIndex <= right) {
            //左边当前值小于等于右边
            if(arr[l] <= arr[rightStartIndex]) {
                temp[tempIndex] = arr[l];
                tempIndex++;
                l++;
            }else {
                //右边当前值小于左边当前值
                temp[tempIndex] = arr[rightStartIndex];
                tempIndex++;
                rightStartIndex++;
            }
        }
        //如果左边还有剩余未对比的值
        while (l <= mid) {
            temp[tempIndex] = arr[l];
            l++;
            tempIndex++;
        }
        //如果右边还有剩余未对比的值
        while(rightStartIndex<=right) {
            temp[tempIndex] = arr[rightStartIndex];
            rightStartIndex++;
            tempIndex++;
        }

        //拷贝数据到原数组
        tempIndex = 0;
        int tempLeft = left;
        while (tempLeft<=right) {
            arr[tempLeft] = temp[tempIndex];
            tempLeft++;
            tempIndex++;
        }
    }
}
