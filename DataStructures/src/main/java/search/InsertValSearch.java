package search;

/**
 * 查找算法；插值查找
 * 1.主要是根据 int mid = left+1/2(right-left)变化而来，其与二分查找法的唯一区别在于mid的动态变化
 * 2，公式为 int mid = left+(right-left)*(findVal-left)/(right-left)
 * 3.
 */
public class InsertValSearch {

    public static void main(String[] args) {
        int arr[] = {-10,-10,1,3,4,9,10,99,991,991};
        int searchIndex = binarySearch(arr, 0, arr.length - 1, 991);
        System.out.println("当前找到的下标为："+searchIndex);
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
        //防止查找的值不在当前的有序数组范围
        if(left>right || findVal > arr[arr.length-1] || findVal < arr[0]  ) {
            return -1;
        }
        //获取中间index
        int mid = left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
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
}
