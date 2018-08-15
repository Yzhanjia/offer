package code.ch05._05;

/**
 * 给定字符串，如果字符串符合日常书写的整数形式，并属于32位整数的范围
 * 返回字符串所代表的整数值，否则返回0
 * 123 --> 123
 * 023 --> 0
 * -123 --> -123
 */
public class VaildNumString {
    public static int convert(String string) {
        if (string == null || string.equals("")) {
            return 0;
        }
        char[] chars = string.toCharArray();
        //看是否为日常书写的整数形式
        if (!isValid(chars)) {
            return 0;
        }
        boolean posi = chars[0] != '-';//记录正负
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        //将字符串转为数字，并防止溢出
        for (int i = posi ? 0 : 1; i < chars.length; i++) {//负数从第1位开始，正数从第0位开始
            cur = '0' - chars[i];//因为最小值的绝对值大于最大值的绝对值，所以全部以负数记录，以防溢出
            // res的绝对值大于最小值的绝对值的十分之一，当res * 10 后会溢出
            //或者res * 10 + minr 溢出
            if ((res < minq) || (res == minq && cur < minr)) {
                return 0;//不符合即返回0
            }
            res = res * 10 + cur;
        }
        if (posi && res == Integer.MIN_VALUE) {
            return 0;//正数的最大值的绝对值比负数的最小值少1
        }
        return posi ? -res : res;//恢复正负号，正数当前值取反

    }

    /**
     * 判断是否为日常书写的整数形式，不考虑溢出问题
     *
     * @param chars
     * @return
     */
    private static boolean isValid(char[] chars) {
        //第一个非负号或非数字
        if (chars[0] != '-' && (chars[0] < '0' || chars[0] > '9')) {
            return false;
        }
        //只有一个负号或者负号加在0前
        if (chars[0] == '-' && (chars.length == 1 || chars[1] == '0')) {
            return false;
        }
        //第一个数字为0
        if (chars[0] == '0' && chars.length > 1) {
            return false;
        }
        //数字中间不可夹杂非数字字符
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }
}
