package leetcode;
/*
在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，
选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。
如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。

当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。

若函数不能执行有效的转换，返回 0。

说明：

假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 */
public class Atoi {
    public static int myAtoi(String str) {
        int result = 0;
        int current = 0;
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int i = 0;
        int flag = 1;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        if (i < str.length() && str.charAt(i) == '-') {
            flag = -1;
            i++;
        } else if (i < str.length() && str.charAt(i) == '+') {
            i++;
        }
        if (i >= str.length() || !(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
            return 0;
        }
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            current = str.charAt(i) - '0';
            if (result>Integer.MAX_VALUE/10||(result==Integer.MAX_VALUE/10&&current>=8)) {
                return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + current;
            i++;
        }

        return result*flag;

    }
}
