package code.ch04._01;

/**
 * 斐波那契数列 1 1 2 3 5 8 13 21......
 * 补充：可以跨一阶或二阶，返回多少种走法
 * 母牛每年会生1头小母牛，永远不会死，三年后成熟可再生小母牛
 */
public class Fibonacci {
    /**
     * 暴力递归，直接套公式
     *
     * @param n 第 n 项
     * @return
     */
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    /**
     * 从左到右依次求出 时间复杂度为O(N)
     *
     * @param n
     * @return
     */
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    /**
     * 矩阵求解，找出特征矩阵，时间复杂度为O(logN)
     *
     * @param n
     * @return
     */
    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};//特征矩阵
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];//res[0][0]为F(n - 1) res[1][0]为 F(n - 2)
    }

    /**
     * 矩阵连乘
     *
     * @param base  矩阵
     * @param power 连乘的幂
     * @return
     */
    private static int[][] matrixPower(int[][] base, int power) {
        int[][] res = new int[base.length][base[0].length];
        //初始化矩阵为单位矩阵,相当于1
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = base;
        //指数连乘，减少相乘的次数
        for (; power != 0; power >>= 1) {
            if ((power & 1) != 0) {//二进制对应的位为1
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    /**
     * 两个矩阵相乘
     *
     * @param m1
     * @param m2
     * @return
     */
    private static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 台阶走法,暴力递归
     *
     * @param n
     * @return
     */
    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return s1(n - 1) + s1(n - 2);
    }

    /**
     * 迭代解法
     *
     * @param n
     * @return
     */
    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res += pre;
            pre = tmp;
        }
        return res;
    }

    /**
     * 矩阵求法
     *
     * @param n
     * @return
     */
    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        //F(1)为1，F(2)为2
        return 2 * res[0][0] + res[1][0];
    }

    /**
     * 多少母牛
     *
     * @param n
     * @return
     */
    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    /**
     * @param n
     * @return
     */
    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res += prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    /**
     * @param n
     * @return
     */
    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }
}
