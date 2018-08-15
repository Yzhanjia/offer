package code.ch01._10;

import java.util.LinkedList;

/**
 * 最大值减去最小值小于或等于num的子数组数量
 */
public class GetTheNum {
    public int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<>();//双端队列记录每个子数组的最小值
        LinkedList<Integer> qmax = new LinkedList<>();//记录每个子数组的最大值
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {//j不断向右移动
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();//队尾值最小，如果不是最小，则弹出
                }
                qmin.addLast(j);
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();//队尾为最大，否则弹出
                }
                qmax.addLast(j);
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;//最大值与最小值的和大于所给值，j向右扩张结束
                }
                j++;//j向右扩展
            }
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.pollFirst() == i) {
                qmax.pollFirst();
            }
            res += j - i;//有j-i个数组符合要求
            i++;//i向右缩小
        }
        return res;
    }
}
