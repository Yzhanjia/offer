package code.ch05._14;

/**
 * 给定一个字符串，判断是不是整体有效的括号字符串
 * 即左右括号对称
 * 补充：返回最长的有效括号子串
 */
public class ValidString {
    public static void main(String[] args) {
//        System.out.println(isValid("()()"));
        System.out.println(maxLength("(()"));
    }

    public static boolean isValid(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        char[] chars = str.toCharArray();
        int status = 0;//记录左右括号的数量，右括号在任何时候不能大于左括号，且最后左右括号数目相等
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ')' && chars[i] != '(') {
                return false;
            }
            if (chars[i] == ')' && --status < 0) {
                return false;
            }
            if (chars[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }

    public static int maxLength(String str) {
        if (str == null && str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length];//记录每个必须以str[i]结尾的最长有效括号子串长度
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                pre = i - dp[i - 1] - 1;//完整括号子串的上一个
                if (pre >= 0 && chars[pre] == '(') {
                    //有可能chars[pre]的前面有完整括号子串
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);//返回记录最大的长度
        }
        return res;
    }
}
