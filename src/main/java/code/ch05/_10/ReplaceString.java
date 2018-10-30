package code.ch05._10;

/**
 * 字符串的调整和替换
 * 给定一个字符类型的数组，右半区全是空字符，左半区不含空字符
 * 请将左半区中的所有空格字符替换成“%20”
 * 补充问题：
 * 给定一个字符类型的数组，只含有数字和“*”
 * 把所有“*”移到左边，数字移到右边，不改变数字字符出现的顺序
 */
public class ReplaceString {
    public static void main(String[] args) {
        char[] chars1 = {'s', ' ', 'w', 's', ' ', 'l'};
        char[] chars2 = {'1', '2', '*', '2', '*'};
        char[] chars = replace(chars1);
        for (char c : chars
        ) {
            System.out.print(c);
        }
        modify(chars2);
        System.out.println();
        for (char c : chars2
        ) {
            System.out.print(c);
        }
    }

    /**
     * 替换空字符
     *
     * @param chars
     */
    public static char[] replace(char[] chars) {
        if (chars == null || chars.length == 0) {
            return chars;
        }
        int num = 0;
        int len = 0;
        for (len = 0; len < chars.length && chars[len] != 0; len++) {
            if (chars[len] == ' ') {
                num++;//统计空格字符的数量
            }
        }
//        System.out.println(len);
//        System.out.println(num);
        int j = len + num * 2;//新的字符数组的长度
        char[] newChars = new char[j];
        j--;
        for (int i = len - 1; i > -1; i--) {//从最后一个字符开始倒着遍历
            if (chars[i] != ' ') {
//                System.out.println(j);
                newChars[j--] = chars[i];
            } else {
                newChars[j--] = '0';
                newChars[j--] = '2';
                newChars[j--] = '%';
            }
        }
        return newChars;
    }

    /**
     * 移动字符
     *
     * @param chars
     */
    public static void modify(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        int j = chars.length - 1;
        for (int i = chars.length - 1; i > -1; i--) {
            if (chars[i] != '*') {//遇到*不复制
                chars[j--] = chars[i];
            }
        }
        while (j > -1) {
            chars[j--] = '*';//再统一复制*
        }
    }
}
