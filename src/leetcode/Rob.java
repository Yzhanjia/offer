package leetcode;
/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2:

输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = = 2 + 9 + 1 = 12 。
 */

public class Rob {
    public static int[] result;
    public int rob(int[] nums) {
        result = new int[nums.length];//保存结果
        for (int i = 0; i < nums.length; i++) {
            result[i] = -1;//初始化，以便后期判断是否遍历过
        }
        return solve(nums,nums.length-1);

    }

    private int solve(int[] nums, int index) {
        if (index<0){
            return 0;
        }
        if (result[index]>=0){//已遍历完全部数据
            return result[index];
        }
        /*
       从最后一个店开始，有两种可能，选择这家和下下家，或者不选这家
       选下下家是同样的情况，递归
       比较这两种的大小，可得结果
       result数组存储每次的结果
         */
        result[index] = Math.max(solve(nums,index-1),solve(nums,index-2) + nums[index]);
        return result[index];
    }
}
