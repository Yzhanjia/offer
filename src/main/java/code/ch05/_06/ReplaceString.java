package code.ch05._06;

/**
 * 将string 中的from字符串替换为 to字符串
 * from无重复字符
 * 出现多个from只替换一个
 */
public class ReplaceString {
    public static String replace(String str, String from, String to) {
        if (str == null || from == null || str.equals("") || from.equals("")) {
            return str;
        }
        char[] strChars = str.toCharArray();
        char[] fromChars = from.toCharArray();
        int match = 0;//匹配到from的位置
        for (int i = 0; i < strChars.length; i++) {
            if (strChars[i] == fromChars[match]) {
                if (match == fromChars.length) {
                    //match == from.length(),证明匹配到from
                    clear(strChars, i, fromChars.length);//将form的字符全部替换为空
                    match = 0;//重置，从头开始遍历
                }
            } else {
                //如果当前字符刚好等于from的第一个字符从form头开始遍历
                if (strChars[i] == fromChars[0]) {
                    i--;
                }
                match = 0;
            }
        }
        String res = "";// 最终要返回的字符串
        String cur = "";// 非from的部分
        for (int i = 0; i < strChars.length; i++) {
            //将空字符替换为to
            if (strChars[i] != 0) {
                cur = cur + String.valueOf(strChars[i]);
            }
            //从头开始或者上一个字符非0，证明是原先的from的上一个字符，直接替换一个
            if (strChars[i] == 0 && (i == 0 || strChars[i - 1] != 0)) {
                res = res + cur + to;
                cur = "";
            }
        }
        if (!cur.equals("")) {
            res = res + cur;//替换最后一组
        }
        return res;
    }

    /**
     * 将from字符串全替换为空字符
     *
     * @param strChars 要替换的源字符串
     * @param end      from最后一个字符在原字符串的
     *                 位置
     * @param length   from的长度
     */
    private static void clear(char[] strChars, int end, int length) {
        while (length-- != 0) {
            strChars[end--] = 0;
        }
    }


}
