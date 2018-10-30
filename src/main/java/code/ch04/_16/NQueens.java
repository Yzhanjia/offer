package code.ch04._16;

/**
 * N皇后问题
 * 任意两个皇后不能再同行同列同一斜线上
 * 有多少种摆法
 */
public class NQueens {
    public static void main(String[] args) {
        System.out.println(validQueensTypes1(8));
        System.out.println(validQueensTypes2(8));
    }

    public static int validQueensTypes1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];//记录第i行的皇后所在的列数
        return process1(record, 0, n);
    }

    private static int process1(int[] record, int i, int n) {
        if (i == n) {
            return 1;//到第n行，全部排列完毕
        }
        int res = 0;
        for (int j = 0; j < n; j++) {//逐列迭代
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(record, i + 1, n);//进入下一行
            }
        }
        return res;
    }

    /**
     * 判断是否符合N皇后要求，不在同行同列同一斜线
     *
     * @param record
     * @param i
     * @param j
     * @return
     */
    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 位运算加速
     *
     * @param n
     * @return
     */
    public static int validQueensTypes2(int n) {
        //int 类型只有32位，位运算只支持32位记录
        if (n < 1 || n > 32) {
            return 0;
        }
        int upperLim = n == 32 ? -1 : (1 << n) - 1;//将对应位数的位置置为1
        return process2(upperLim, 0, 0, 0);
    }

    /**
     * @param upperLim    表示当前行哪些位置是可以放置皇后的，1表示可以放置
     * @param colLim      递归到上一行为止，哪些列已放置，1表示已放置
     * @param leftDiaLim  受之前的行数影响，在左斜线哪些列不能放置，1表示不可放置
     * @param rightDiaLim 受之前的行数形象，在右斜线哪些列不能放置，1表示不可放置
     * @return
     */
    private static int process2(int upperLim, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == upperLim) {
            return 1;
        }
        int pos = 0;//表示可放置的位置，1表示可以放置
        int mostRightOne = 0;//表示pos中，最右边的1所在位置，然后从右到左一次筛选pos可选的位置进行尝试
        pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(upperLim, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }


}
