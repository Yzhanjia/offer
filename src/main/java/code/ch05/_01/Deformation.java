package code.ch05._01;

/**
 * 给定两个词，判断是否互为异形词
 */
public class Deformation {
    /**
     * 若字符类型不止256，可用HashMap代替数组
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isDeformation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < chars1.length; i++) {
            map[chars1[i]]++;
        }
        for (int i = 0; i < chars2.length; i++) {
            if (map[chars2[i]]-- == 0) {
                return false;
            }
        }
        return true;
    }
}
