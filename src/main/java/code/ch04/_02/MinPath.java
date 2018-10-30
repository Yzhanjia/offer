package code.ch04._02;

/**
 * 给定一个矩阵，从左上角运动到右下角
 * 路径上所有数字的累加和为路径和，求最小路径和
 */
public class MinPath {
    /**
     * 经典动态规划
     *
     * @param m
     * @return
     */
    public int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        //初始化边界
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //左边和上边较小的一个加上当前值
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 空间压缩矩阵
     *
     * @param m
     * @return
     */
    public static int minPath2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int more = Math.max(m.length, m[0].length);//行数与列数较大的
        int less = Math.min(m.length, m[0].length);
        boolean rowmore = more == m.length;
        int[] arr = new int[less];//辅助数组的长度为行数列数较小的一个
        arr[0] = m[0][0];
        for (int i = 1; i < less; i++) {
            arr[i] = arr[i - 1] + (rowmore ? m[0][i] : m[i][0]);//从左到右计算
        }
        for (int i = 1; i < more; i++) {//从上往下滚动
            arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
            for (int j = 1; j < less; j++) {
                arr[j] = Math.min(arr[j - 1], arr[j]) + (rowmore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less - 1];
    }
}
