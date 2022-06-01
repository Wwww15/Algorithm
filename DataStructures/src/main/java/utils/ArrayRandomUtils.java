package utils;

/**
 * 随机生成数数组
 */
public class ArrayRandomUtils {

    /**
     * 生成随机数
     */
    public static  int[] random(int size) {
        int arr[] = new int[size];
        for (int i = 0;i<size;i++ ) {
            arr[i] = (int) (Math.random()*20000);
        }
        return arr;
    }
}
