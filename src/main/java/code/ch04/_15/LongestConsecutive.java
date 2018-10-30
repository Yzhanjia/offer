package code.ch04._15;

import java.util.HashMap;

/**
 * 给定无序数组arr,返回其中最长的连续序列的长度
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 2, 3};
        System.out.println(longestConsecutive(arr));
    }

    /**
     * 通过map记录，不断合并能连续的序列
     *
     * @param arr
     * @return
     */
    public static int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 1;
        HashMap<Integer, Integer> map = new HashMap<>();//记录key所在的序列的最长连续序列长度
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {//只处理之前未出现过的值
                map.put(arr[i], 1);
                if (map.containsKey(arr[i] - 1)) {//合并较小的前面部分和较大的后面部分
                    max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
                }
                if (map.containsKey(arr[i] + 1)) {
                    max = Math.max(max, merge(map, arr[i], arr[i] + 1));
                }
            }
        }
        return max;
    }

    private static int merge(HashMap<Integer, Integer> map, int less, int more) {
        int left = less - map.get(less) + 1;//较小数字的前面部分的最小值
        int right = more + map.get(more) - 1;//较大数字的后面部分的最大值
        int len = right - left + 1;
        map.put(left, len);
        map.put(right, len);//更新两端的对应值,中间值不更新也没有用
        return len;
    }
}
