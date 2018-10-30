package code.ch04._11;

/**
 * 数字字符串转换为字母组合的种数
 * 给定一个全为数字字符的字符串，如果某一个或相邻两个字符组成的值在1-26之间
 * 则该子串可以转为一个字母，"1"-->"A"等
 */
public class NumToLetter {
    public static void main(String[] args) {
        System.out.println(numToLetter1("11"));
        System.out.println(numToLetter2("11"));
    }

    /**
     * 暴力递归
     *
     * @param num
     * @return
     */
    public static int numToLetter1(String num) {
        if (num == null || num.length() == 0) {
            return 0;
        }
        return process(num, 0);
    }

    /**
     * 表示num[0..i]已经转换完成，剩下字符未转换完成，最终合法的转换方式有多少种
     *
     * @param num
     * @param i
     * @return
     */
    private static int process(String num, int i) {
        if (i == num.length()) {
            return 1;
        }
        if (num.charAt(i) == '0') {
            return 0;//不可能以0开头
        }
        int res = process(num, i + 1);//方法种数至少与i+1相同
        if (i + 1 < num.length() && (num.charAt(i) - '0') * 10 + (num.charAt(i + 1) - '0') < 27) {
            res += process(num, i + 2);//多加上下一字符已转换完成的情况
        }
        return res;
    }

    /**
     * 只需记录p(i+1)与p(i+2)
     *
     * @param num
     * @return
     */
    public static int numToLetter2(String num) {
        if (num == null || num.equals("")) {
            return 0;
        }
        int cur = num.charAt(num.length() - 1) == '0' ? 0 : 1;
        int next = 1;
        int tmp = 0;
        for (int i = num.length() - 2; i >= 0; i--) {
            if (num.charAt(i) == '0') {
                next = cur;
                cur = 0;
            } else {
                tmp = cur;
                if ((num.charAt(i) - '0') * 10 + (num.charAt(i + 1) - '0') < 27) {
                    cur += next;
                }
                next = tmp;
            }
        }
        return cur;

    }

}
