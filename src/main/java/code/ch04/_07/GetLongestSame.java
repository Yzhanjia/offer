package code.ch04._07;

/**
 * 给定两个字符串，返回两个字符串的最长公共子串
 */
public class GetLongestSame {
    public static void main(String[] args) {
        System.out.println(lace("AB412C", "AB34"));
    }

    public static String lace(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return "";
        }
        int[][] dp = getDp(str1, str2);
        int m = str1.length() - 1;
        int n = str2.length() - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;//记录结果字符串的当前字符的位置
        while (index >= 0) {
            if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else {
                res[index--] = str1.charAt(m);
                n--;
                m--;
            }
        }
        return String.valueOf(res);
    }

    /**
     * 获取dp数组来记录最长公共序列
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int[][] getDp(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];
        dp[0][0] = str1.charAt(0) == str2.charAt(0) ? 1 : 0;
        for (int i = 1; i < str1.length(); i++) {
            dp[i][0] = Math.max(dp[0][0], str1.charAt(i) == str2.charAt(0) ? 1 : 0);
        }
        for (int j = 1; j < str2.length(); j++) {
            dp[0][j] = Math.max(dp[0][0], str1.charAt(0) == str2.charAt(j) ? 1 : 0);
        }
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }
}
