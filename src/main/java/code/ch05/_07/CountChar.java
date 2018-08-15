package code.ch05._07;

/**
 * 求一个字符串的统计字符串
 * aaabbadddffc --> a_3_b_2_a_1_d_3_f_2_c_1
 * 进阶：由统计字符串求index位置的字符
 */
public class CountChar {
    public static String getCountString(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        char[] chars = str.toCharArray();
        String res = String.valueOf(chars[0]);
        int num = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                res = concat(res, String.valueOf(num), String.valueOf(chars[i]));
                num = 1;
            } else {
                num++;
            }
        }
        //再连接最后一个字符的数量
        return concat(res, String.valueOf(num), "");
    }

    private static String concat(String s1, String s2, String s3) {
        return s1 + "_" + s2 + (s3.equals("") ? s3 : "_" + s3);
    }

    /**
     * 求index位置的字符
     */
    public char getCharAt(String countStr, int index) {
        if (countStr == null || countStr.equals("")) {
            return 0;
        }
        char[] chars = countStr.toCharArray();
        boolean stage = true;//stage为true表示处在遇到字符的状态，false表示目前处在遇到连续字符的统计数字阶段
        char cur = 0;
        int num = 0;
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '_') {
                stage = !stage;//转换状态
            } else if (stage) {
                sum += num;//记录统计数字和
                if (sum > index) {
                    //若超过，则表示所求为上一个重复字符
                    return cur;
                }
                num = 0;
                cur = chars[i];//记录当前字符，重新开始
            } else {
                num = num * 10 + chars[i] - '0';//将字符串转为数字
            }
        }
        return sum + num > index ? cur : 0;//最后一个字符的统计不会加到sum上，单独加
    }
}
