package search;

/**
 * 查找算法：顺序查找（线性查找）
 */
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,599,999,123,-1,-90};
        int searchIndex = seqSearch(arr, 99);
        System.out.println("当前找到的下标为："+searchIndex);
    }

    public static int seqSearch(int[] arr,int findVal) {
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]==findVal) {
                return i;
            }
        }
        return -1;
    }
}
