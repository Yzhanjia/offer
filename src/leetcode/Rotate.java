package leetcode;
/*
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释:
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
说明:

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的原地算法。
 */

public class Rotate {
    public void rotate(int[] nums, int k) {
//        k = k %(nums.length);//k可能大于数组长度
//
//        if (nums.length!=0){
//            int temp;
//            for (int i = 0; i < k ; i++) {
//                temp = nums[nums.length-1];//从最后一个开始移动
//                for (int j = nums.length-1; j > 0 ; j--) {//每次都将整个数组移动
//                    nums[j] = nums[j-1];//全部元素向后移动一位，循环K次
//                }
//                nums[0] = temp;//移动一位，第一位是上次的最后一位
//
//            }
//        }
        k = k % nums.length;//必须，没有会数组越界

        reverse(nums);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);

    }

    private void reverse(int[] nums, int begin, int end) {
        int length = end - begin + 1;
        int half = length  / 2;
        for (int i = 0; i < half ; i++) {
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
            begin++;
            end--;
        }
    }

    private void reverse(int[] nums) {
        reverse(nums,0,nums.length-1);
    }
}
