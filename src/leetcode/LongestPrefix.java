package leetcode;
/*
求出字符串数组相同的最长前缀
 */
public class LongestPrefix {
    public static void main(String[] args) {
        String[]str = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(str));
    }
    public static String longestCommonPrefix(String[] strs) {
        if (strs==null||strs.length==0){
            return "";//数组为空，返回空字符串
        }
        if (strs.length==1){//若只有一个，自该字符串为最长前缀
            return strs[0];
        }
        int i = 0;
        String result = getString(strs[i],strs[++i]);//先求前两个的最长前缀
        while (i+1<strs.length){
            result = getString(result,strs[++i]);//递归实现，将该最长前缀与下一字符串比较
        }
        return result;

    }

    private static String getString(String str1, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = Math.min(str1.length(),str2.length());//只需迭代最短字符串长度
        int i = 0;
        while (i<n&&str1.charAt(i)==str2.charAt(i)){
            stringBuilder.append(str1.charAt(i));//拼接，找出最长前缀
            i++;
        }
        return stringBuilder.toString();

    }
}
