package code.ch01._09;

import java.util.Stack;

/**
 * 求最大子矩阵的大小
 * 整型矩阵map，其中的值只有为0和1
 * 求所有矩阵区域的最大的单矩形区域为1的数量
 */
public class MaxRec {
    public static void main(String[] args) {
        int[][] test = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(maxRecSize(test));
    }

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {//分行求最大值
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;//求高
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);//求每一行的面积最大值与当前最大值比较
        }
        return maxArea;
    }

    private static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();//栈顶到栈底依次递减（记录位置）
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                //找到每一个高度左边第一个比它大或等于的值与右边最后一个比它大的值
                //最左扩展到k + 1 的位置
                //最右扩展到i - 1 的位置
                //（i-1）-(k+1) +1
                int curArea = (i - k - 1) * height[j];//当前高度扩展出去的面积
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        //遍历结束后，栈不为空，将栈中的值逐个弹出
        //最右即是其能扩展的最远部分
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}
