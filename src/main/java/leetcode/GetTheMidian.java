package leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * <p>
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * <p>
 * 你可以假设 nums1 和 nums2 不同时为空。
 */
public class GetTheMidian {
    public static double getTheMidian(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {//保证不出现负数
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int imax = m;
        int imin = 0;
        int i = 0;
        int j = 0;
        while (imin <= imax) {
            i = (imin + imax) / 2;
            j = (m + n + 1) / 2 - i;
            if (i < imax && B[j - 1] > A[i]) {//i太小，需要向右移动
                imin = imin + 1;
            } else if (i > imin && A[i - 1] > B[j]) {//j太小，需要增大j,即减小i
                imax = imax - 1;
            } else {
                //找到对应的值，左右两边的数字个数相等，或左边个数比右边个数大1，且左边最大值小于右边最小值
                int maxLeft = 0;//左半部分最大的值
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;//奇数返回左边最大值即所求值，左边个数大于右边个数
                }
                int maxRight = 0;
                if (i == m) {
                    maxRight = B[j];
                } else if (j == n) {
                    maxRight = A[i];
                } else {
                    maxRight = Math.min(A[i], B[j]);
                }
                return (maxLeft + maxRight) / 2.0;
            }
        }
        return 0.0;
    }
}
