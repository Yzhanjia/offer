package code.ch04._10;

/**
 * 龙与地下城
 * 一个二维数组，从左上角到右下角，与所经过的数字相加
 * 在每个位置不小于1，则要到达右下角，初始至少为多少
 */
public class MinHP {
    public static void main(String[] args) {
        int[][] map = {
                {-1, -3, -3},
                {-1, -1, -10},
                {20, -3, -1}
        };
        System.out.println(minHP1(map));
        System.out.println(minHP2(map));
    }

    /**
     * 从右到左，从下到上
     * 经典动态规划的方法
     *
     * @param map
     * @return
     */
    public static int minHP1(int[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) {
            return 1;
        }
        int row = map.length;
        int col = map[0].length;
        int[][] dp = new int[row--][col--];
        dp[row][col] = map[row][col] > 0 ? 1 : 1 - map[row][col];
        for (int j = col - 1; j >= 0; j--) {//最下一条边
            dp[row][j] = Math.max(dp[row][j + 1] - map[row][j], 1);
        }
        int right = 0;//代表向右运动
        int down = 0;//代表向下运动
        for (int i = row - 1; i >= 0; i--) {
            dp[i][col] = Math.max(dp[i + 1][col] - map[i][col], 1);
            for (int j = col - 1; j >= 0; j--) {
                right = Math.max(dp[i][j + 1] - map[i][j], 1);
                down = Math.max(dp[i + 1][j] - map[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }

    /**
     * 空间压缩
     *
     * @param map
     * @return
     */
    public static int minHP2(int[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) {
            return 1;
        }
        int more = Math.max(map.length, map[0].length);
        int less = Math.min(map.length, map[0].length);
        boolean rowmore = map.length == more;
        int[] dp = new int[less];
        int row = 0;
        int col = 0;
        dp[less - 1] = map[map.length - 1][map[0].length - 1] > 0 ? 1 : 1 - map[map.length - 1][map[0].length - 1];
        for (int j = less - 2; j >= 0; j--) {
            row = rowmore ? more - 1 : j;
            col = rowmore ? j : more - 1;
            dp[j] = Math.max(dp[j + 1] - map[row][col], 1);
        }
        int choosen1 = 0;
        int choosen2 = 0;
        for (int i = more - 2; i >= 0; i--) {
            row = rowmore ? i : less - 1;
            col = rowmore ? less - 1 : i;
            dp[less - 1] = Math.max(dp[less - 1] - map[row][col], 1);
            for (int j = less - 2; j >= 0; j--) {
                row = rowmore ? i : j;
                col = rowmore ? j : i;
                choosen1 = Math.max(dp[j] - map[row][col], 1);//向下运动
                choosen2 = Math.max(dp[j + 1] - map[row][col], 1);//向右运动
                dp[j] = Math.min(choosen1, choosen2);
            }
        }
        return dp[0];
    }
}
