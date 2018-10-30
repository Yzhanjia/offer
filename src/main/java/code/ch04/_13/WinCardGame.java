package code.ch04._13;

/**
 * 排成一条线的纸牌博弈问题
 * 给定一个整型数组，每次只能从两边拿牌，目的自己的分数最大化，对手分数最小化
 * 求获胜者最后的分数
 */
public class WinCardGame {
    public static void main(String[] args) {
        int[] arr = {1, 2, 100, 4};
        System.out.println(Win1(arr));
        System.out.println(Win2(arr));
    }

    /**
     * 暴力递归
     *
     * @param arr
     * @return
     */
    public static int Win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 如果当前的人后拿，能获得什么分数
     * 从i -->j
     *
     * @param arr
     * @param i
     * @param j
     * @return
     */
    private static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        //对手知道自己只能从其中挑一，让自己获得的分数较小
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));//对手会让自己获得分数较小
    }

    /**
     * 如果当前的人先拿，最后能获得什么分数
     *
     * @param arr
     * @param i
     * @param j
     * @return
     */
    private static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        //让自己在后取的时候获得较大的值
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    /**
     * 动态规划的方法
     *
     * @param arr
     * @return
     */
    public static int Win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];//只有一个字符
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }
}
