package code.ch04._07;

/**
 * 返回两个字符串的最长公共子串
 */
public class LongestSameSubString {
    public static void main(String[] args) {
        System.out.println(lcst2("5512345677", "123555123456"));
    }

    /**
     * 经典的动态规划的方法，空间复杂度为N*M
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String lcst1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return "";
        }
        int[][] dp = getDp(str1, str2);
        int max = 0;//获取子串长度
        int end = 0;//获取子串最后一个位置的下标
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    end = i;
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);//substring的第二参数是子串的后一位置
    }

    /**
     * 经典动态规划的方法
     *
     * @param str1
     * @param str2
     * @return
     */
    private static int[][] getDp(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < str2.length(); j++) {
            if (str1.charAt(0) == str2.charAt(j)) {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

    /**
     * 空间复杂度为1的斜线法
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String lcst2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return "";
        }
        int row = 0;
        int col = str2.length() - 1;//斜线从右上角开始，不断向左下角移动
        int max = 0;//最大长度
        int end = 0;
        while (row < str1.length()) {
            int i = row;
            int j = col;
            int len = 0;
            while (i < str1.length() && j < str2.length()) {
                if (str1.charAt(i) != str2.charAt(j)) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > max) {
                    max = len;
                    end = i;
                }
                i++;
                j++;
            }
            if (col > 0) {//先向左移动，移动到最左端再向下移动，第一个值都是两边的值
                col--;
            } else {
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }
}
