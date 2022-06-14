package kmp;

/**
 * 算法：暴力匹配
 */
public class ViolenceMatchDemo {

    public static void main(String[] args) {
        String srcStr = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String searchStr = "硅";
        int searchResult = violenceMatch(srcStr,searchStr);
        System.out.println(searchResult);
    }

    /**
     * 暴力匹配字符串
     * 1.下标i定义原字符串srcStr下标
     * 2.下标j定义查找的字符串searchStr下标
     * 3.当且仅当j = searchStr.length()时，返回对应下标,否则返回-1
     * @return
     */
    public static int violenceMatch(String srcStr,String searchStr) {
        //记录srcStr遍历到的位置
        int i = 0;
        //记录searchStr遍历到的位置
        int j = 0;
        while(i<srcStr.length() && j<searchStr.length()) {
            //如果有匹配的字符
            if(srcStr.charAt(i) == searchStr.charAt(j)) {
                i++;
                j++;
            }else { //如果没有匹配的字符
                //这里需要回溯，回溯到初始匹配上的index+1
                i = i-(j-1);
                j = 0;
            }
        }
        //找到,j需要等于当前查找的字符串长度，因为每次找到后，j++了
        if(j == searchStr.length()) {
            return i-j;
        }
        //没找到
        return -1;
    }
}
