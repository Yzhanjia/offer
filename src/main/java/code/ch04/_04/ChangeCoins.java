package code.ch04._04;

/**
 * 求目标数组组合成目标的钱数的方法数
 */
public class ChangeCoins {
    /**
     * 暴力递归
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    /**
     * 使用map记录已经计算过的值，记忆化搜索
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim];//记录递归过程，防止重复计算
        return process2(arr, 0, aim, map);

    }

    private static int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];//记录用其余货币组成剩余的目标货币的方法数
                if (mapValue != 0) {//代表该值曾经计算过
                    res += mapValue == -1 ? 0 : mapValue;//为-1代表无法组成剩下的目标货币，否则直接使用记录过的值
                } else {//未曾计算过
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;//无法组成为-1，可以组成则将计算过的方法数记录
        return res;
    }

    /**
     * 动态规划方法
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;//组成目标货币数为0的方法数为1，初始化第一列
        }
        for (int j = 0; arr[0] * j <= aim; j++) {//初始化第一行
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for (int i = 1; i < arr.length; i++) {//从左到右，从上到下计算
            for (int j = 1; j < aim; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * 替代原先的累加部分
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coins4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;//组成目标货币数为0的方法数为1，初始化第一列
        }
        for (int j = 0; arr[0] * j <= aim; j++) {//初始化第一行
            dp[0][arr[0] * j] = 1;
        }

        for (int i = 1; i < arr.length; i++) {//从左到右，从上到下计算
            for (int j = 1; j < aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;//替代原先的累加
            }
        }
        return dp[arr.length - 1][aim];

    }

    /**
     * 压缩空间的方法
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coins5(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

}
