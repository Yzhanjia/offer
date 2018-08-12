package code.ch05._02;

/**
 * 给定一个字符串，求其中全部数字串所代表的数字之和
 * “-”单数表示负数，双数表示正数
 */
public class AddStringSum {
    public static int numSum(String string) {
        if (string == null) {
            return 0;
        }
        char[] chars = string.toCharArray();//可直接使用string.charAt
        int res = 0;//累加结果
        int num = 0;//字符串遇到的数字
        boolean posi = true;//记录正负,正数为true
        int cur = 0;//当前字符与数字的相对距离
        for (int i = 0; i < chars.length; i++) {
            cur = chars[i] - '0';
            if (cur < 0 || cur > 9) {
                res += num;
                num = 0;//遇到非数字，证明数字部分已经结束，相加并重置当前数字
                if (chars[i] == '-') {//记录负号的个数
                    if (i - 1 > -1 && chars[i - 1] == '-') {
                        posi = !posi;//上一个字符也是'-'换号，连续多个负号需变换多次
                    } else {
                        posi = false;
                    }
                } else {
                    posi = true;
                }
            } else {
                num = num * 10 + (posi ? cur : -cur);
            }
        }
        res += num;
        return res;
    }
}
