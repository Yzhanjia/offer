package code.ch04._03;

/**
 * 换钱的最小货币数
 * 给定数组，每张货币可以使用任意张，组成目标地钱数
 * 进阶：每张纸币只有一张
 */
public class MinCoins {
    public static int minCoins1(int[] arr, int aim) {
        if (arr.length == 0 || arr == null || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;//表示无法组成该钱数
        int[][] dp = new int[n][aim + 1];//0表示组成为0的货币数，行为不同货币，不同列表示组成的和
        for (int j = 1; j <= aim; j++) {//初始化第一行数据
            dp[0][j] = max;
            if (j - arr[0] >= 0 && dp[0][j - arr[0]] != max) {
                dp[0][j] = dp[0][j - arr[0]] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) {
                    left = dp[0][j - arr[i]] + 1;//dp数组的左边一个位置
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);//与上一行数据比较
            }
        }
        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    /**
     * 压缩空间的方法
     *
     * @param arr
     * @param aim
     */
    public static int minCoins2(int[] arr, int aim) {
        if (arr.length == 0 || arr == null || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[] dp = new int[aim + 1];
        for (int j = 1; j <= aim; j++) {
            dp[j] = max;
            if (j - arr[0] >= 0 && dp[j - arr[0]] > 0) {
                dp[j] = dp[j - arr[0]] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
                    left = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(left, dp[j]);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }

    /**
     * 进阶问题：每张纸币只有一张
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins(int[] arr, int aim) {
        if (arr.length == 0 || arr == null || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][aim + 1];
        for (int j = 1; j <= aim; j++) {
            dp[0][j] = max;
        }
        if (arr[0] <= aim) {
            dp[0][arr[0]] = 1;
        }
        int leftup = 0;//左上角的某个位置的值
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                leftup = max;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) {
                    leftup = dp[i][j - arr[i]] + 1;
                }
                leftup = Math.min(leftup, dp[i - 1][j]);
            }
        }
        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    /**
     * 进阶问题压缩空间
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins4(int[] arr, int aim) {
        if (arr.length == 0 || arr == null || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[] dp = new int[aim + 1];

        for (int j = 1; j <= aim; j++) {
            dp[j] = max;
        }
        if (dp[arr[0]] <= aim) {
            dp[arr[0]] = 1;
        }
        int leftup = 0;
        for (int i = 1; i < n; i++) {
            for (int j = aim; j > 0; j--) {
                leftup = max;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
                    leftup = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(leftup, dp[j]);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }
}
