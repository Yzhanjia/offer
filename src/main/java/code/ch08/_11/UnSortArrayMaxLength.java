package code.ch08._11;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个无序数组，元素可正可负可0，给定整数k，求所有子数组中累加和为k的最长
 * 子数组的长度
 */
public class UnSortArrayMaxLength {
    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //key为sum第一次出现的位置，value为逐步的累加和，只记录第一次和为sum的位置，因为是求最长长度
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//一个数都不加时，累加和为0
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                // k = arr[j~i] = sum(i) - sum(j-1)
                len = Math.max(i - map.get(sum - k), len);//找出最长长度
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);//第一次出现记录进map

            }
        }
        return len;
    }
}
