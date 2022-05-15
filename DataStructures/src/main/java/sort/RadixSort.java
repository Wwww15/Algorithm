package sort;

import java.util.Arrays;

/**
 * 排序算法：基数排序
 * 思路：
 *    1）用固定的10个桶来装数据，桶里面可以放排序的数组长度个数字
 *    2) 根据最大数的位数来定义最外面的循环
 *    3) 用个数、十位。。。等位数上的值逐个比较大小
 *    4）将对比出来的值，放在桶里面，并用一个临时的一维数组存储当前的下标
 */
public class RadixSort {

    public static void main(String[] args) {
        int arr[] = { 53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序
     */
    public static void radixSort(int[] arr) {
        //获取当前的最大数
        int max = arr[0];
        for (int i=1;i<arr.length;i++) {
            if(arr[i]>max) {
                max = arr[i];
            }
        }
        //获取当前最大数的位数
        int maxLength = (max+"").length();
        //定义一个二维数组用来作为桶
        int bucketArr[][] = new int[10][arr.length];
        //定义一个一维数组用来放置当前桶里面的下标
        int indexArr[] = new int[10];
        for (int i=0,n=1;i<maxLength;i++,n*=10) {
            for (int j =0;j<arr.length;j++) {
                //得到当前位上的余数
                int remain = arr[j]/n % 10;
                //根据余数放东西在桶里面
                bucketArr[remain][indexArr[remain]] = arr[j];
                //当前桶的下标增长1
                indexArr[remain]++;
            }
            //设置一个临时的下标
            int index = 0;
            //一轮过后，对桶遍历，并将值复制到原数组
            for (int j = 0;j<indexArr.length;j++) {
                //当前桶里面有值
                if(indexArr[j] !=0) {
                    //遍历当前桶
                    for (int k=0;k<indexArr[j];k++) {
                        arr[index++] = bucketArr[j][k];
                    }
                }
                //当前桶处理完后，重置为0
                indexArr[j] = 0;
            }
        }
    }
}
