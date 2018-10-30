package code.ch04._09;

/**
 * 给定三个字符串str1,str2,aim，如果aim包含且仅含st1,str2的所有字符
 * 且所有字符的顺序和原先的两个字符串中的顺序相同则会交错组成
 * 判断aim是否为交错组成
 */
public class CrossString {
    public static void main(String[] args) {
        System.out.println(isCross2("AB", "12", "A1B2"));
    }

    /**
     * 经典动态规划的方法
     *
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public static boolean isCross1(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }
        if (aim.length() != str1.length() + str2.length()) {
            return false;
        }
        //dp[i][j]表示aim[i+j-1]能否被str1[i-1]和str2[j-1]交错组成
        boolean[][] dp = new boolean[str1.length() + 1][str2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length(); i++) {
            if (str1.charAt(i - 1) != aim.charAt(i - 1)) {
                break;//直接结束。剩下全为false
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= str2.length(); j++) {
            if (str2.charAt(j - 1) != aim.charAt(j - 1)) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if ((dp[i - 1][j] && str1.charAt(i - 1) == aim.charAt(i + j - 1))
                        || (dp[i][j - 1] && str2.charAt(j - 1) == aim.charAt(i + j - 1))) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    /**
     * 空间压缩的方法
     *
     * @param str1
     * @param str2
     * @param aim
     * @return
     */
    public static boolean isCross2(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }
        if (aim.length() != str1.length() + str2.length()) {
            return false;
        }
        String longs = new String(str1.length() >= str2.length() ? str1 : str2);
        String shorts = new String(str1.length() >= str2.length() ? str2 : str1);
        boolean[] dp = new boolean[shorts.length() + 1];
        dp[0] = true;
        for (int j = 1; j <= shorts.length(); j++) {
            if (shorts.charAt(j - 1) != aim.charAt(j - 1)) {
                break;
            }
            dp[j] = true;
        }
        for (int i = 1; i <= longs.length(); i++) {
            dp[0] = dp[0] && longs.charAt(i - 1) == aim.charAt(i - 1);
            for (int j = 1; j <= shorts.length(); j++) {
                //必须，要更新值，原先不用是因为有默认值
                dp[j] = (dp[j] && longs.charAt(i - 1) == aim.charAt(i + j - 1))
                        || (dp[j - 1] && shorts.charAt(j - 1) == aim.charAt(i + j - 1));
            }
        }
        return dp[shorts.length()];
    }
}
