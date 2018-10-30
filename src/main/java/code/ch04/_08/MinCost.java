package code.ch04._08;

/**
 * 给定两个字符串，与一个字符插入，删除，替换分别的代价
 * 返回str1编辑为str2的最小代价
 */
public class MinCost {
    public static void main(String[] args) {
        System.out.println(minCost2("ac", "adc", 5, 3, 2));
    }

    /**
     * @param str1
     * @param str2
     * @param ic   插入代价
     * @param dc   删除代价
     * @param rc   替换代价
     * @return
     */
    public static int minCost1(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        int row = str1.length() + 1;
        int col = str2.length() + 1;//包括为空字串的情况
        int[][] dp = new int[row][col];
        for (int i = 1; i < row; i++) {
            dp[i][0] = i * dc;
        }
        for (int j = 0; j < col; j++) {
            dp[0][j] = j * ic;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);//str1[0..i]先删除编辑为str1[0..i-1],再转为str2[0..j]
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);//str1[0..i]先编辑为str2[0..j-1]，再插入转为str2[0..j]
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 空间压缩方法
     *
     * @param str1
     * @param str2
     * @param ic
     * @param dc
     * @param rc
     * @return
     */
    public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        String longs = new String(str1.length() >= str2.length() ? str1 : str2);
        String shorts = new String(str1.length() >= str2.length() ? str2 : str1);
        if (str1.length() < str2.length()) {//长度长的转化为长度较短的，如果str1较短，则dp的含义变为从str2转为str1
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length() + 1];
        for (int i = 1; i <= shorts.length(); i++) {
            dp[i] = i * ic;
        }
        for (int i = 1; i <= longs.length(); i++) {
            int pre = dp[0];//记录左上角的值
            dp[0] = dc * i;//重置第一个
            for (int j = 1; j <= shorts.length(); j++) {
                int tmp = dp[j];//在dp[j]更新前保存下来
                if (longs.charAt(i - 1) == shorts.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], tmp + dc);
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                pre = tmp;
            }
        }
        return dp[shorts.length()];
    }
}
