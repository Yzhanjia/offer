package leetcode;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[n][m];
        for (int j = 0; j < m; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }

    public int uniquePaths2(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int longs = Math.max(n, m);
        int shorts = Math.min(n, m);
        int[] dp = new int[shorts];
        for (int j = 0; j < shorts; j++) {
            dp[j] = 1;
        }
        for (int i = 1; i < longs; i++) {
            dp[0] = 1;
            for (int j = 1; j < shorts; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[shorts - 1];
    }
}
