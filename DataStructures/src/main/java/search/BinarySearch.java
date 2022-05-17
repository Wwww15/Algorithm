package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询算法：二分查找算法（折半算法）
 * 注意：二分查找算法需要当前的数组是有序的
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {-10,-10,1,3,4,9,10,99,99,991,991};
        int searchIndex = binarySearch(arr, 0, arr.length - 1, 99);
        System.out.println("当前找到的下标为："+searchIndex);
//        List searchList = binarySearchAll(arr,0, arr.length - 1,991);
//        System.out.println(searchList.toString());
    }

    /**
     * 这里采用递归的二分查找法
     * 获取查询单个值
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int binarySearch(int arr[],int left,int right,int findVal) {
        System.out.println("进来了一个~");
        //首先判断是否已经查找完毕
        if(left>right) {
            return -1;
        }
        //获取中间index
        int mid = (left+right)/2;
        //判断当前寻找的值所在位置
        //在右边
        if(findVal>arr[mid]) {
            //继续往右边查找
           return binarySearch(arr,mid+1,right,findVal);
        } else if(findVal<arr[mid]) {
            //继续往左边查找
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            //找到了
            return mid;
        }
    }

    /**
     * 查询多个值
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List binarySearchAll(int arr[], int left, int right, int findVal) {
        //首先判断是否已经查找完毕
        if(left>right) {
            return new ArrayList();
        }
        //获取中间index
        int mid = (left+right)/2;
        //判断当前寻找的值所在位置
        //在右边
        if(findVal>arr[mid]) {
            //继续往右边查找
            return binarySearchAll(arr,mid+1,right,findVal);
        } else if(findVal<arr[mid]) {
            //继续往左边查找
            return binarySearchAll(arr,left,mid-1,findVal);
        }else {
            //找到了
            //先往左边找找
            ArrayList<Integer> findList = new ArrayList<>();
            int searchLeft = mid-1;
            while (searchLeft>=0 && arr[searchLeft]==findVal) {
                findList.add(searchLeft);
                searchLeft--;
            }
            //把当前找到的加入
            findList.add(mid);
            //往右边找找
            int searchRight = mid+1;
            while (searchRight<arr.length && arr[searchRight]==findVal) {
                findList.add(searchRight);
                searchRight++;
            }
            return findList;
        }
    }
}
