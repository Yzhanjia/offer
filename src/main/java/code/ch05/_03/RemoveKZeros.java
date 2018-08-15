package code.ch05._03;

/**
 * 去掉字符串中连续出现的K个0的子串
 */
public class RemoveKZeros {
    //    public static void main(String[] args) {
//        char i = 0;
//        System.out.println(i);
//    }
    public static String removeKzeros(String string, int k) {
        if (string == null || k < 1) {
            return string;
        }
        char[] chars = string.toCharArray();
        int count = 0, start = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                count++;
                start = start == -1 ? i : start;//start==-1,证明之前未遇到连续0的子串
            } else {
                if (count == k) {
                    while (count-- != 0) {
                        chars[start++] = 0;//0的位置为null
                    }
                    count = 0;
                    start = -1;//不管是不是k个0都重置
                }
            }
        }
        if (count == k) {
            while (count-- != 0) {
                chars[start++] = 0;//去掉最后一组连续k个为0，但无k+1个字符的子串
            }
        }
        return String.valueOf(chars);
    }
}
