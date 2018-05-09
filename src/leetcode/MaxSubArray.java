package leetcode;
/*
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        /*
        若有无效输入的情况，因设置一个全局变量来记录，区分返回0时的与实际返回0
         */
        int curSum = 0;
        int greatestSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
           if (curSum<=0){
               curSum = nums[i];//若当前和小于等于零，则当前和重置为当前指针所指向值
               //加上小于等于零的值，一定小于等于原先的总和，所以可以替换为当前值
           }else {
               curSum += nums[i];//大于零则累加
           }
           if (curSum>greatestSum){
               greatestSum = curSum;//由于原先最大值设置为最小值，所以一定会记录第一次当前和
               //若当前和大于原先最大值，将最大值替换为当前和
           }

        }
        return greatestSum;

    }
}
