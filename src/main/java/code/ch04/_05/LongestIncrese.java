package code.ch04._05;

/**
 * 给定数组arr，返回arr的最长递增子数列
 */
public class LongestIncrese {
    public int[] lis1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp1(arr);//获取dp数组
        return generateLIS(arr, dp);//由dp数组重构子序列
    }

    /**
     * 根据dp数组重构增长子序列
     *
     * @param arr
     * @param dp
     * @return
     */
    private int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;//记录最长的增长长度
        int index = 0;//记录最长增长子序列的最后一个位置
        for (int i = 0; i < dp.length; i++) {
            if (len < dp[i]) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {//需满足的两个条件
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }

    /**
     * 直接迭代产生dp数组，dp[i]表示以arr[i]结尾的最长增长数列的长度
     * 时间复杂度为N^2
     *
     * @param arr
     * @return
     */
    private int[] getdp1(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    public int[] lis2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp2(arr);
        return generateLIS(arr, dp);
    }

    /**
     * 时间复杂度为NlogN的获取dp数组的方法
     *
     * @param arr
     * @return
     */
    private int[] getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];//有效序列，ends[i]表示i + 1长度的增长序列的最小结尾数
        ends[0] = arr[0];
        dp[0] = 1;
        int l = 0;
        int r = 0;
        int m = 0;
        int right = 0;//有效序列的最后一个位置
        for (int i = 0; i < arr.length; i++) {
            l = 0;//每次从第一个开始
            r = right;
            while (l <= r) {//二分法查找有效序列的第一个大于当前数字的数字的位置
                m = (l + r) / 2;
                if (ends[m] < arr[l]) {
                    l = m + 1;//如果比之前的序列都大，ends有效区长度+1，当前的数字置于ends有效的最后
                } else {
                    r = m - 1;//如果找到比当前数字大的位置，则在有效区替换该数字
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;

    }
}
