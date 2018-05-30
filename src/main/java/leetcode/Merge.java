package leetcode;
/*
给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:

初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
示例:

输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
 */

public class Merge {
    public static void main(String[] args) {
        int[]nums1 = {1,2,3,0,0,0};
        int[]nums2 = {2,5,6};
        merge(nums1,3,nums2,3);
    }
    /*
    原地归并排序
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0,j = 0;
       int[] tmp = new int[m];
        for (int k = 0; k < m; k++) {//复制nums1
            tmp[k] = nums1[k];
        }
        for (int k = 0; k < n+m; k++) {//遍历复制数组
            if (i>=m){//遍历完原先nums1中的数据
                nums1[k] = nums2[j++];//剩余全是nums2的数据，nums2剩余数据均大于原先nums1的数据
                //将nums2对应数据复制并移动指针
            }else if (j>=n){//遍历完nums2的数据
                nums1[k] = tmp[i++];
            }else if (nums2[j]<tmp[i]){//如果nums2对应数据小于原先nums1对应数据
                nums1[k] = nums2[j++];//复制nums2中的数据，并移动nums2对应指针
            }else
                nums1[k] = tmp[i++];
        }

    }
}
