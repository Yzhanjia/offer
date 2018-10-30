package leetcode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int maxLength = 0;
        String maxString = null;
        boolean[][] dp = new boolean[s.length()][s.length()];//表示从i到j是否为回文字符串
        for (int i = 0; i < s.length(); i++) {//只有一个字符为回文字符串
            dp[i][i] = true;
            maxString = s.substring(i, i + 1);
            maxLength = 1;
        }
        for (int i = 0; i < s.length() - 1; i++) {//两个字符只要相等就是回文字符串
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxString = s.substring(i, i + 2);
                maxLength = 2;
            }
        }
        for (int len = 3; len <= s.length(); len++) {
            for (int i = 0, j; (j = i + len - 1) < s.length(); i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j] && maxLength < len) {
                        maxString = s.substring(i, j + 1);
                        maxLength = len;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return maxString;
    }
}
