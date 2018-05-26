package leetcode;

import java.util.HashMap;
import java.util.Map;
/*
 求数组中和为target的任一数对，要输出位置，若先排序，则当前位置会被打乱
 */

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        twoSum(nums,9);
    }
    public static int[] twoSum(int[] nums, int target){
//        Arrays.sort(nums);//先将数组排序
//        int [] sum =new int[2];
//        int i =0;//数组第一个位置
//        int j = nums.length-1;//数组最后一个位置
//        while (nums[i] + nums[j] != target){//若两个数和不为target，移动指针
//            if(!(i<nums.length&&j>=0)){//数组已遍历完，不存在
//                break;
//            }
//
//            if (nums[i] + nums[j] < target){//向后移动头指针
//                i++;
//            }else {
//                j--;//向前移动尾指针
//            }
//        }
//        sum[0] = i;
//        sum[1] = j;
//        return sum;
/*
用map存储target的差与对应位置，若后面包含该差，则找到另一数字
 */
        Map<Integer,Integer> map = new HashMap<>();
        int[]sum = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                sum[0] = map.get(nums[i]);
                sum[1] = i;
                break;
            }else {
                map.put(Integer.valueOf(target - nums[i]),Integer.valueOf(i));

            }
        }
        return sum;

    }
}
