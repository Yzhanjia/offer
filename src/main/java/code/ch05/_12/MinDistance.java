package code.ch05._12;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定字符串数组strs，给定str1和str2，返回str1和str2的最小距离
 * 若其中一个不存在，或者不在strs中，返回-1
 * 进阶：将查询时间复杂度降为O(1)
 */
public class MinDistance {
    //第一个str1,第二个str2,Integer为distance
    private static HashMap<String, HashMap<String, Integer>> record;

    public MinDistance(String[] strArr) {
        record = new HashMap<>();
        HashMap<String, Integer> indexMap = new HashMap<>();//每个字符串的最新位置
        for (int i = 0; i < strArr.length; i++) {
            String curStr = strArr[i];
            update(indexMap, curStr, i);
            indexMap.put(curStr, i);
        }
    }

    /**
     * @param indexMap 存有字符串位置的map
     * @param curStr   遍历到的当前字符串
     * @param i        当前字符串的位置
     */
    private void update(HashMap<String, Integer> indexMap, String curStr, int i) {
        if (!record.containsKey(curStr)) {
            record.put(curStr, new HashMap<>());
        }
        HashMap<String, Integer> strMap = record.get(curStr);//当前字符串对应的map
        for (Map.Entry<String, Integer> lastEntry : indexMap.entrySet()//遍历存有字符串位置的map
        ) {
            String key = lastEntry.getKey();//map中已有字符串
            int index = lastEntry.getValue();//该字符串的位置
            if (!key.equals(curStr)) {//求与除自身外的字符串的位置
                HashMap<String, Integer> lastMap = record.get(key);//获取对应字符串的map
                int curMin = i - index;//求出当前字符串与遍历到的字符串位置的距离
                if (strMap.containsKey(key)) {//当前字符串与当前遍历到的字符串的距离是否已存在
                    int preMin = strMap.get(key);//若存在，获得之前的最小距离
                    if (curMin < preMin) {//若当前最小值小于原先的最小值，更新map
                        strMap.put(key, curMin);//当前字符串的map添加与遍历到的字符串的距离
                        lastMap.put(curStr, curMin);//更新遍历到的字符串与当前字符串的距离
                    }
                } else {//原先另一位置并未存入map
                    strMap.put(key, curMin);//直接更新两个字符串的距离
                    lastMap.put(curStr, curMin);
                }
            }
        }
    }

    public static int minDistance(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        if (str1.equals(str2)) {
            return 0;
        }
        if (record.containsKey(str1) && record.get(str1).containsKey(str2)) {
            return record.get(str1).get(str2);
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] strs = {"1", "3", "3", "3", "2", "3", "1"};
        MinDistance minDistance = new MinDistance(strs);
        System.out.println(minDistance("1", "2"));
        //System.out.println(minDistance(strs, "1", "21"));
    }

    /**
     * 找出两个字符串的最小距离
     *
     * @param strs
     * @param str1
     * @param str2
     * @return
     */
    public static int minDistance(String[] strs, String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        if (str1.equals(str2)) {
            return 0;
        }
        int last1 = -1;
        int last2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(str1)) {
                min = Math.min(min, last2 == -1 ? min : i - last2);
                last1 = i;
            }
            if (strs[i].equals(str2)) {
                min = Math.min(min, last1 == -1 ? min : i - last1);
                last2 = i;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
