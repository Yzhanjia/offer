package code.ch03._06;

import code.ch03.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定二叉树的头结点与32位整数sum,求累加和为sum的最长路径的长度
 */
public class TreeNodeMaxLength {
    public static int getMaxLength(Node head, int sum) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0);//不添加任何节点时的长度
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    /**
     * @param head   要遍历的节点
     * @param sum    目标sum
     * @param preSum 上一个sum
     * @param level  当前层
     * @param maxLen 最大长度
     * @param sumMap 记录sum与level的对应map
     * @return
     */

    private static int preOrder(Node head, int sum, int preSum, int level, int maxLen, Map<Integer, Integer> sumMap) {
        if (head == null) {
            return maxLen;
        }
        // k = arr[j~i] = sum(i) - sum(j-1)
        int curSum = preSum + head.value;
        if (!sumMap.containsKey(curSum)) {
            sumMap.put(curSum, level);
        }
        if (sumMap.containsKey(curSum - sum)) {
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }
        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);
        if (level == sumMap.get(curSum)) {
            //除去当前层，不同路径不可相加，除去时代表路径当前层已遍历，返回后会返回上一层，
            // 从其他路径继续
            sumMap.remove(curSum);
        }
        return maxLen;
    }
}
