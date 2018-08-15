package code.ch01._07;

import java.util.LinkedList;

/**
 * 生成窗口最大值数组
 * 整型数组。窗口的大小为w，窗口每次向右移动一个位置
 */

public class MaxWindow {
    public static void main(String[] args) {
        int[] test = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = getMaxWindow(test, 3);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();//双端队列记录
        int[] res = new int[arr.length - w + 1];//一共生成n - w + 1 个最大值
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //队头数据要大于后面的数据
            //从队尾弹出元素直到当前值小于队列上一个元素
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            //将当前元素位置添加到队列中
            qmax.addLast(i);
            //窗口不覆盖队头，将最大队列队头移除
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            //将队头元素记录位置的对应值记录到目标数组中
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}
