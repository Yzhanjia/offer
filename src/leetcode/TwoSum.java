package leetcode;

import java.util.Arrays;
/*
 求数组中和为target的任一数对
 */

public class TwoSum {
    public int[] twoSum(int[] nums, int target){
        Arrays.sort(nums);//先将数组排序
        int [] sum =new int[2];
        int i =0;//数组第一个位置
        int j = nums.length-1;//数组最后一个位置
        while (nums[i] + nums[j] != target){//若两个数和不为target，移动指针
            if(!(i<nums.length&&j>=0)){//数组已遍历完，不存在
                break;
            }

            if (nums[i] + nums[j] < target){//向后移动头指针
                i++;
            }else {
                j--;//向前移动尾指针
            }
        }
        sum[0] = i;
        sum[1] = j;
        return sum;

    }
}
