package code.ch05._13;

/**
 * 添加最少字符使字符串整体都是回文字符串
 * 1.给定字符串，可以在任意位置添加字符，请返回添加字符最少的情况下的回文字符串
 * 2.给定字符串与该字符串最长回文子序列字符串，请返回回文字符串并使时间复杂度比原问题低
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(getPalindrome("ABCEA"));
        System.out.println(getPalindrome("A1B21C", "121"));
    }

    /**
     * 获得动态规划数组,记录每个子串需添加地字符数
     *
     * @param str
     * @return
     */
    public static int[][] getDP(char[] str) {
        //dp[i][j]表示str[i]到str[j]需要添加的字符数
        //只有一个字符，不需添加
        //两个字符，相等，不需添加，不相等，添加1个
        int[][] dp = new int[str.length][str.length];
        for (int j = 1; j < str.length; j++) {
            dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i--) {
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1];//除去两端添加的字符数
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;//除去一端，再最后两端添加相同字符
                }
            }
        }
        return dp;
    }

    /**
     * 根据dp矩阵将字符串转为回文字符串
     *
     * @param str
     * @return
     */
    public static String getPalindrome(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        char[] chars = str.toCharArray();
        int[][] dp = getDP(chars);//dp数组
        char[] res = new char[chars.length + dp[0][chars.length - 1]];//0 -> chars.length-1 需要添加的字符数
        int i = 0;
        int j = chars.length - 1;//原字符串的下标
        int resl = 0;
        int resr = res.length - 1;//返回结果的下标
        while (i <= j) {
            if (chars[i] == chars[j]) {
                res[resl++] = chars[i++];
                res[resr--] = chars[j--];
            } else if (dp[i][j - 1] < dp[i + 1][j]) {//两端字符不一样，根据除去一端较小的子串两端添加除去的字符
                res[resl++] = chars[j];
                res[resr--] = chars[j--];
            } else {
                res[resl++] = chars[i];
                res[resr--] = chars[i++];
            }
        }
        return String.valueOf(res);
    }

    /**
     * 进阶问题，根据回文子串构造新的回文字符串
     *
     * @param str
     * @param strlps
     * @return
     */
    public static String getPalindrome(String str, String strlps) {
        if (str == null || str.equals("")) {
            return "";
        }
        char[] chars = str.toCharArray();
        char[] lps = strlps.toCharArray();
        char[] res = new char[chars.length * 2 - lps.length];
        int charsl = 0;
        int charsr = chars.length - 1;//原字符串的下标
        int lpsl = 0;
        int lpsr = lps.length - 1;//回文子串的下标
        int resl = 0;
        int resr = res.length - 1;//结果字符串的下标
        int tmpl = 0;
        int tmpr = 0;
        while (lpsl <= lpsr) {
            tmpl = charsl;
            tmpr = charsr;
            while (chars[charsl] != lps[lpsl]) {
                charsl++;
            }
            while (chars[charsr] != lps[lpsr]) {
                charsr--;
            }
            set(res, resl, resr, chars, tmpl, charsl, charsr, tmpr);
            int length = (charsl - tmpl) + (tmpr - charsr);//左外层子串的长度加上右外层的长度
            resl += length;//右移
            resr -= length;//左移
            res[resl++] = chars[charsl++];
            res[resr--] = chars[charsr--];//复制原先回文子串的部分
            lpsl++;
            lpsr--;//对称移动
        }
        return String.valueOf(res);
    }

    /**
     * @param res
     * @param resl
     * @param resr
     * @param chars
     * @param ls    左外层子串的开头
     * @param le    左外层子串的结尾
     * @param rs    右外层子串的开头
     * @param re    右外层子串的结尾
     */
    private static void set(char[] res, int resl, int resr, char[] chars, int ls, int le, int rs, int re) {
        //从左往右添加左外层子串
        for (int i = ls; i < le; i++) {
            res[resl++] = chars[i];
            res[resr--] = chars[i];
        }
        //从右往左添加右外层子串
        for (int i = re; i > rs; i--) {
            res[resl++] = chars[i];
            res[resr--] = chars[i];
        }
    }
}
