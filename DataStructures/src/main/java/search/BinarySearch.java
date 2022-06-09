package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询算法：二分查找算法（折半算法）
 * 注意：二分查找算法需要当前的数组是有序的
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {-10,-10,1,3,4,9,10,99,99,991,991,1000};
//        int searchIndex = binarySearch(arr, 0, arr.length - 1, 99);
        int searchIndex = binarySearchNoRecursion(arr, 991);
        System.out.println("当前找到的下标为："+searchIndex);
//        List searchList = binarySearchAll(arr,0, arr.length - 1,991);
//        System.out.println(searchList.toString());
    }

    /**
     * 这里采用递归的二分查找法
     * 获取查询单个值
     * 1.利用左右下标之和/2的方式进行递归
     * 2.left>right则递归完成
     * 3.查询的值大于当前中间值，则left=mid+1
     * 4.查询的值小于当前中间的值，则right=mid-1
     * 5.继续递归
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
     * 1.在二分查找基础上，增加了对已经找到的下标左右两边继续对比查找的方式，完成后放入List返回
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

    /**
     * 二分查找——非递归的方式
     * 总结：都是利用left和right的变化来查找对应的targetVal
     * @param arr 数组
     * @param targetVal 对应查询的值
     * @return 返回的下标
     */
    public static int binarySearchNoRecursion(int[] arr,int targetVal) {
        int left = 0;
        int right = arr.length-1;
        while (left<=right) {
            //中间index
            int mid = (left+right)/2;
            //判断
            //目标值小于中间，向左查
            if(arr[mid]>targetVal) {
                right = mid-1;
            }else if(arr[mid]<targetVal) {  //目标值大于中间，向右查
                left = mid+1;
            }else { //找到返回
                return mid;
            }
        }
        //未找到返回-1
        return -1;
    }
}
