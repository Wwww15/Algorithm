package search;

import java.util.Arrays;

/**
 * 查找算法：斐波那契查找
 * 总结：根据斐波那契数列的规则查找对应数列值,然后找到mid
 * 1.首先生成一个斐波那契数列
 * 2.根据斐波那契数列找到对应的k值（k值主要是斐波那契数列的下标），斐波那契：{1,1,2,3,5,8,,13....}，相邻两个数比列接近黄金比例，也叫黄金分割法
 * 3.根据公式 F(K) = F(K-1)+F(K-2) 推算出 mid = left+F[k-1]-1，另外，对应的数组长度也就像F(K) = F(K-1)+F(K-2)这样构成， F(K-1)和F(K-2)之间就是mid
 * 4.根据公式不断向左和向右查找值，直到left>right
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8,9,99,99,100,101,9999};
        int index = fibonacciSearch(arr, 999999);
        System.out.println("当前找到的下标为："+index);
    }

    /**
     * 生成斐波那契{1,1,2,3,5,8,,13....}
     * 1.前两个数为1，要首先生成
     * 2.后面的规则F(K) = F(K-1)+F(K-2)
     * @param maxSize
     * @return
     */
    public static int[] fib(int maxSize) {
        int fib[] = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i=2;i<maxSize;i++) {
            fib[i] = fib[i-1]+fib[i-2];
        }
        return fib;
    }

    /**
     *  查找对应的值
     *  1.根据斐波那契找到对应的k值
     *  2.找到K值后如果发现对应的数组长度不够，增加对应的数组长度，数值放最大的数（保证有序）
     *  3.根据K值找到对应的mid
     *  4.判断left<=right，循环查找
     *  5.判断左右大小，确认遍历的方向
     * @param arr
     * @param findVal
     * @return
     */
    public static int fibonacciSearch(int arr[],int findVal) {
        int right = arr.length-1;
        int left = 0;
        int k = 0;
        //这里怎么确认最大值在斐波那契数列内，有问题
        int[] fib = fib(10000);
        //找到K值,fib[k]-1表示下标
        while (right>fib[k]-1) {
            k++;
        }
        //这里重新给原数组赋值，保证满足斐波那契的长度
        arr = Arrays.copyOf(arr,fib[k]);
        //替换高位上的值，用最大的值
        for (int i = right+1;i<fib[k];i++) {
            arr[i] = arr[right];
        }
        //开始查找对应的值
        while(left<=right) {
            //找到中间值mid
            int mid = left + fib[k-1]-1;
            //向左查找
            if(findVal<arr[mid]) {
                right = mid-1;
                k-=1;
            }else if(findVal>arr[mid]) {
                //向右查找
                left = mid+1;
                k-=2;
            }else {
                //找到，判断在原数组中还是赋值后的数组中（这里有补充值）
                if(mid<=right) {
                    return mid;
                }else
                {
                   return right;
                }
            }
        }
        //未找到
        return -1;
    }
}
