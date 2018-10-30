package code.ch04._06;

/**
 * 汉诺塔问题
 * 进阶：给定数组，代表汉诺塔最优移动过程的中间步骤，返回这是第几个状态
 */
public class Hanoi {
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "left", "mid", "right");
        }
    }

    /**
     * 递归过程
     *
     * @param n
     * @param from
     * @param mid
     * @param to
     */
    private static void func(int n, String from, String mid, String to) {
        if (n == 1) {
            System.out.println("move from " + from + " to " + to);
        } else {
            func(n - 1, from, to, mid);
            func(1, from, mid, to);
            func(n - 1, mid, from, to);
        }
    }

    /**
     * 进阶问题，根据数组返回这是汉诺塔移动过程的哪个状态
     *
     * @param arr
     * @return
     */
    public static int step1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return process(arr, arr.length - 1, 1, 2, 3);
    }

    private static int process(int[] arr, int i, int from, int mid, int to) {
        if (i == -1) {
            return -1;
        }
        if (arr[i] != from && arr[i] != to) {//最优移动步骤不可能在中间的位置
            return -1;
        }
        int rest = process(arr, i - 1, mid, from, to);//除去最大一层剩下的情况
        if (rest == -1) {
            return -1;
        }
        return 1 << i + rest;//最大的圆盘移动到当前位置至少需要2^(i-1)，剩下为上一层的步骤数
    }

    /**
     * 非递归的方式
     *
     * @param arr
     * @return
     */
    public static int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while (i >= 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {//最大的圆盘在右边，则次最大的当前应该在中间
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {//最大的圆盘左边，剩下的圆盘应该移动到中间
                tmp = to;
                to = mid;
            }
            mid = tmp;//交换目标地或出发地
            i--;//进入下一步骤
        }
        return res;
    }
}
