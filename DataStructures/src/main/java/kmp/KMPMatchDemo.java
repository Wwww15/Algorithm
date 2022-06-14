package kmp;

import java.util.Arrays;

/**
 * 算法：KMP算法
 * 1.特点：
 * 1）kmp算法解决了暴力匹配算法回溯的问题，引入了部分匹配表的概念
 */
public class KMPMatchDemo {

    public static void main(String[] args) {
        String srcStr = "BBC ABCDAB ABCDABCDABDE";
        String searchStr = "ABCDABD";
        int[] searchPartMatch = kmpMatchPart(searchStr);
        //打印部分匹配表
        System.out.println(Arrays.toString(searchPartMatch));
        int searchResult = kmpMatch(srcStr, searchStr, searchPartMatch);
        //打印查找结果
        System.out.println(searchResult);
    }

    /**
     * 通过部分匹配表查找对应的字符串
     * @param srcStr 原字符串
     * @param searchStr 查找的字符串
     * @param searchPartMatch 查找字符串的部分匹配表
     */
    public static int kmpMatch(String srcStr,String searchStr,int[] searchPartMatch) {
        for (int i = 0,j = 0; i < srcStr.length() ; i++) {
            //通过部分匹配表，获取当前需要匹配的下标，跳过不需要匹配的下标，主要调整查找的字符串下标,原理 TODO
            while (j > 0 && srcStr.charAt(i) != searchStr.charAt(j)) {
                j = searchPartMatch[j-1];
            }
            //如果原字符串与查找的字符串字符相等，则j++
            if(srcStr.charAt(i) == searchStr.charAt(j)) {
                j++;
            }
            //已经找到
            if(j == searchStr.length()) {
                //这里因为i没加，所以需要+1，例如：j=3,i=2
                return i-j+1;
            }
        }
        return -1;
    }

    /**
     * kmp查找字符串部分匹配表
     * 1.按照前缀数组和后缀数组交集的最大字符串长度生成部分匹配表，例如：
     * 1) "AA":最后一次，前缀为[A]，后缀为[A]，则部分匹配表为[0,1]
     * 2) "AAAB"：最后一次，前缀为[A，AA，AAA】，后缀为[AAB，AB，B]，则部分匹配表为[0,1,2,0]
     *
     * @param searchStr
     * @return
     */
    public static int[] kmpMatchPart(String searchStr) {
        //定义一个存储部分匹配表的数组
        int[] searchPartMatch = new int[searchStr.length()];
        //当前第一个部分匹配表的值肯定为0,
        searchPartMatch[0] = 0;
        //这里i表示字符串searchStr的下标
        //j表示当前位置的部分匹配表的值
        for (int i = 1, j = 0; i < searchStr.length(); i++) {
            //判断当前是否有部分匹配的值，原理 TODO
            while (j > 0 && searchStr.charAt(i) != searchStr.charAt(j)) {
                j = searchPartMatch[j-1];
            }
            //如果对应位置字符相等，则部分匹配表值++
            if (searchStr.charAt(i) == searchStr.charAt(j)) {
                j++;
            }
            searchPartMatch[i] = j;
        }
        return searchPartMatch;
    }


}
