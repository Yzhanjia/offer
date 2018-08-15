package code.ch05._04;

/**
 * 判断两个字符串是否互为旋转词
 * 把字符串前面任意部分挪到后面形成的字符串叫做该字符串的旋转词
 */
public class IsRotation {
    public static void main(String[] args) {
        System.out.println(isRotation("12345", "34512"));
    }

    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b; // 若ab互为旋转词，则字符串b2包含另一字符串
        int begin = b2.indexOf(a);
        return begin != -1;
    }
}
