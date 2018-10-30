package code.ch04._12;

/**
 * 表达式得到期望结果的组成种数
 * 1表示真，0表示假，&（与）,|（或）,^（异或）
 * 给定表达式和期望值desired
 * 求有多少种方式返回期望值
 */
public class GetTheResult {
    public static void main(String[] args) {
        System.out.println(getDesired1("1|0^1&1", true));
        System.out.println(getDesired2("1|0^1&1", true));
    }

    /**
     * 暴力递归
     *
     * @param express
     * @return
     */
    public static int getDesired1(String express, boolean desired) {
        if (express == null || express.length() == 0) {
            return 0;
        }
        if (!isValid(express)) {
            return 0;
        }
        return process(express, desired, 0, express.length() - 1);
    }

    /**
     * 迭代过程，分为左右两部分，分情况求种数
     *
     * @param express
     * @param desired
     * @param l
     * @param r
     * @return
     */
    private static int process(String express, boolean desired, int l, int r) {
        if (l == r) {//单个字符为数字
            if (express.charAt(l) == '1') {
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }
        //为逻辑运算符
        int res = 0;
        for (int i = l + 1; i < r; i++) {
            if (desired) {
                switch (express.charAt(i)) {
                    case '&':
                        res += process(express, true, l, i - 1) * process(express, true, i + 1, r);
                        break;
                    case '|':
                        res += process(express, true, l, i - 1) * process(express, true, i + 1, r);
                        res += process(express, false, l, i - 1) * process(express, true, i + 1, r);
                        res += process(express, true, l, i - 1) * process(express, false, i + 1, r);
                        break;
                    case '^':
                        res += process(express, true, l, i - 1) * process(express, false, i + 1, r);
                        res += process(express, false, l, i - 1) * process(express, true, i + 1, r);
                        break;
                }
            } else {
                switch (express.charAt(i)) {
                    case '&':
                        res += process(express, true, l, i - 1) * process(express, false, i + 1, r);
                        res += process(express, false, l, i - 1) * process(express, true, i + 1, r);
                        res += process(express, false, l, i - 1) * process(express, false, i + 1, r);
                        break;
                    case '|':
                        res += process(express, false, l, i - 1) * process(express, false, i + 1, r);
                        break;
                    case '^':
                        res += process(express, true, l, i - 1) * process(express, true, i + 1, r);
                        res += process(express, false, l, i - 1) * process(express, false, i + 1, r);
                        break;
                }
            }
        }
        return res;
    }

    private static boolean isValid(String express) {
        //字符个数必须为奇数
        if ((express.length() & 1) == 0) {
            return false;
        }
        //偶数位必须为0或1
        for (int i = 0; i < express.length(); i += 2) {
            if (express.charAt(i) != '0' && express.charAt(i) != '1') {
                return false;
            }
        }
        //奇数位必须为逻辑运算符
        for (int i = 1; i < express.length(); i += 2) {
            if ((express.charAt(i) != '|') && ((express.charAt(i) != '^')) && (express.charAt(i) != '&')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 动态规划
     *
     * @param express
     * @param desired
     * @return
     */
    public static int getDesired2(String express, boolean desired) {
        if (express == null || express.length() == 0) {
            return 0;
        }
        if (!isValid(express)) {
            return 0;
        }
        int[][] t = new int[express.length()][express.length()];//记录组成true的种数
        int[][] f = new int[express.length()][express.length()];//记录组成组成false的种数
        t[0][0] = express.charAt(0) == '1' ? 1 : 0;
        f[0][0] = express.charAt(0) == '1' ? 0 : 1;
        for (int i = 2; i < express.length(); i += 2) {
            t[i][i] = express.charAt(i) == '1' ? 1 : 0;
            f[i][i] = express.charAt(i) == '1' ? 0 : 1;
            for (int j = i - 2; j >= 0; j -= 2) {//选择之前的数字
                for (int k = j; k < i; k += 2) {//分为左右两部分
                    if (express.charAt(k + 1) == '&') {
                        t[j][i] += t[j][k] * t[k + 2][i];
                        f[j][i] += t[j][k] * f[k + 2][i] + f[j][k] * t[k + 2][i] + f[j][k] * f[k + 2][i];
                    } else if (express.charAt(k + 1) == '^') {
                        t[j][i] += t[j][k] * f[k + 2][i] + f[j][k] * t[k + 2][i];
                        f[j][i] += t[j][k] * t[k + 2][i] + f[j][k] * f[k + 2][i];
                    } else {
                        t[j][i] += t[j][k] * f[k + 2][i] + f[j][k] * t[k + 2][i] + t[j][k] * t[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i];
                    }
                }

            }
        }
        return desired ? t[0][express.length() - 1] : f[0][express.length() - 1];
    }
}
