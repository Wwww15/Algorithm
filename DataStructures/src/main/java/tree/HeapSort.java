package tree;

import utils.ArrayRandomUtils;
import utils.DateUtils;

/**
 * 排序算法：堆排序
 * 1.堆排序特点
 *  1）是一种选择排序
 *  2）时间复杂度均为O（nlogn）
 *  3) 分为大顶堆和小顶堆，大顶堆定义为每个结点的子结点的值都小于或等于其结点的值，小顶堆定义为每个结点的子结点的值都大于或者等于其结点的值
 *  4）如果数组升序排列，选择大顶堆，如果数组降序排列，选择小顶堆
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = ArrayRandomUtils.random(8000000);
        System.out.println(DateUtils.now());
        heapSort(arr);
        System.out.println(DateUtils.now());
    }

    /**
     * 构建当前的大顶堆或者小顶堆
     * 1.首先将当前的数组构建成大顶堆或者小顶堆，构建规则为从左到右，从下往上
     * 2.然后将首结点和尾结点的值进行调换，使最大值或者最小值沉到底部
     * 3.重新根据当前的建堆规则构建堆排序
     * 4.如此反复操作数组实现排序+交换的动作，最终完成数组的整个排序
     * @param arr 当前需要排序的数组（整个数组）
     */
    public static void heapSort(int arr[]) {
        int temp = 0;
        //构建大顶堆，从最左的非叶子结点开始构建，从下往上
        for (int i = arr.length/2-1;i>=0;i--) {
            adjustHeap(arr,i,arr.length);
        }

        // 循环构建大顶堆，交换最大值，使最大值沉到顶部
        for (int i = arr.length-1;i > 0;i--) {
            //首先存储当前尾部结点的值
            temp = arr[i];
            //将顶部的值，交换到尾部
            arr[i] = arr[0];
            //顶部重新赋值，然后构建大顶堆/小顶堆
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
    }

    /**
     * 构建当前的大顶堆或者小顶堆，这里实际上就是交换当前index的值，重新生成当前子树的大顶堆或者小顶堆
     * 1. 将当前结点的值获取，作为临时变量，为了后面可能出现的值交换
     * 2.判断当前结点是否大于左子结点或者右子结点
     * 3.如果是，则直接退出（因为下面的值肯定小于左子结点和右子结点），如果不是，交换当前结点的值，将子结点的值赋给当前结点，并继续往下对比子结点的值
     * 4.完成整个子树的遍历后，赋值给当前的下标index
     * @param arr 当前排序的数组（整个数组）
     * @param i 当前非叶子结点的数组下标
     * @param length 当前需要的排序数组的长度（长度内的有效排序数组，排除尾部已经确认的值）
     */
    public static void adjustHeap(int arr[],int i,int length) {
        //取出当前的index对应的值,为了以后交换
        int temp = arr[i];
        //遍历当前子树，寻找对应的位置,通过左子结点(2n+1)
        for (int k = i*2+1; k < length; k=k*2+1) {
            //判断当前结点的左子结点和右子结点的大小
            if(k+1 < length && arr[k] < arr[k+1]) {
                k++;
            }
            //判断当前最大的子结点是否大于temp,知道找到temp的位置
            if(arr[k]>temp) {
                //子结点大，交换值
                arr[i] = arr[k];
                //继续往下排序，构建大顶堆
                i = k;
            } else {
                //没有，直接退出
                break;
            }
        }
        //已经找到当前temp该存放的位置i
        arr[i] = temp;
    }
}
