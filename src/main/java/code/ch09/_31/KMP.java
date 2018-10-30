package code.ch09._31;

/**
 * 给定两个字符串str和match，长度分别为N和M
 * 如果str中含有match，返回match在str中的开始位置
 * 不含有返回-1
 */
public class KMP {
    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || match.length() < 1 || str.length() < match.length()) {
            return -1;
        }
        char[] ss = str.toCharArray();
        char[] ms = match.toCharArray();
        int si = 0;//匹配到str的下标
        int mi = 0;//匹配到match的下标
        //next[i]表示必须以match[i-1]的后缀子串（不包含match[0]）
        // 与必须以match[0]开头的前缀子串（不包含match[i-1]）的最大匹配长度是多少
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (ms[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    private static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;//后缀子串，从ms[1]开始与ms[0]比较
        int cn = 0;//前缀子串
        while (pos < ms.length) {
            if (ms[cn] == ms[pos - 1]) {
                next[pos] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }
}
