package code.ch05._11;

/**
 * 反转字符串
 * 1.给定字符类型的数组chas,请只做到单词间的逆序 pig loves dog --> dog loves pig
 * 2.给定chas和整数size,请将大小为size的左半区移到右半区
 * 时间复杂度为O(N),空间复杂度为O(1)
 */
public class RoatateWord {
    public static void main(String[] args) {
        char[] chars = {'p', 'i', 'g', ' ', 'l', 'o', 'v', 'e', 's', ' ', 'd', 'o', 'g'};
        rotateWord(chars);
        char[] chars1 = {'A', 'B', 'C', 'D', 'E'};
        rotate2(chars1, 3);
        for (char c : chars
        ) {
            System.out.print(c);
        }
        System.out.println();
        for (char c : chars1
        ) {
            System.out.print(c);
        }
    }

    /**
     * 反转句子中的单词
     *
     * @param chars
     */
    public static void rotateWord(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        reverse(chars, 0, chars.length - 1);//先整体反转
        int l = -1;
        int r = -1;//l -> r 为一个单词的位置
        //再逐个单词反转
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                l = i == 0 || chars[i - 1] == ' ' ? i : l;
                r = i == chars.length - 1 || chars[i + 1] == ' ' ? i : r;
            }
            //逐个单词反转
            if (l != -1 && r != -1) {
                reverse(chars, l, r);
                l = -1;
                r = -1;
            }
        }
    }

    private static void reverse(char[] chars, int start, int end) {
        char temp = 0;
        while (start < end) {
            temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 根据size反转
     *
     * @param chars
     * @param size
     */
    public static void rotate(char[] chars, int size) {
        if (chars == null || chars.length == 0 || size <= 0 || size >= chars.length) {
            return;
        }
        //先部分反转，再整体反转
        reverse(chars, 0, size - 1);
        reverse(chars, size, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
    }

    public static void rotate2(char[] chars, int size) {
        if (chars == null || chars.length == 0 || size <= 0 || size >= chars.length) {
            return;
        }
        int start = 0;
        int end = chars.length - 1;
        int lpart = size;//左半区长度
        int rpart = chars.length - size;//右半区长度
        int s = Math.min(lpart, rpart);//需要反转的部分
        int d = lpart - rpart;//未移动的部分
        while (true) {
            exchange(chars, start, end, s);
            if (d == 0) {//全部已经移动
                break;
            } else if (d > 0) {//左半区大于右半区
                start += s;
                lpart = d;
            } else {//左半区小于右半区
                end -= s;
                rpart = -d;
            }
            s = Math.min(lpart, rpart);
            d = lpart - rpart;
        }
    }

    private static void exchange(char[] chars, int start, int end, int size) {
        int i = end - size + 1;//右半区要移动的第一个位置
        char temp = 0;
        while (size-- != 0) {
            temp = chars[start];
            chars[start] = chars[i];
            chars[i] = temp;
            start++;
            i++;//两个指针同时向右移动
        }
    }

}
