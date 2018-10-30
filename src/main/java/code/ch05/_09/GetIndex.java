package code.ch05._09;

/**
 * 给定一个有序但有空的字符串数组，查找最左目标字符串，即第一次出现的地方
 */
public class GetIndex {
    /**
     * 通过二分法查找
     *
     * @param strs 字符串数组
     * @param str  目标字符串
     * @return
     */
    public static int getIndex(String[] strs, String str) {
        if (strs == null || strs.length == 0 || str == null) {
            return -1;
        }
        int res = -1;
        int left = 0;
        int right = strs.length - 1;
        int mid = 0;
        int i = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (strs[mid] != null && strs[mid].equals(str)) {
                res = mid;
                right = mid - 1;//虽然找到，不一定是最左的字符串
            } else if (strs[mid] != null) {
                if (strs[mid].compareTo(str) < 0) {
                    left = mid - 1;//在右半区开始查找
                } else {
                    right = mid - 1;//在左半区开始查找
                }
            } else {//strs[mid] == null
                i = mid;
                while (strs[i] == null && --i >= left) {//找到非null的值
                }
                if (i < left || strs[i].compareTo(str) < 0) {
                    left = mid + 1;//在右半区开始查找
                } else {
                    res = strs[i].equals(str) ? i : res;//若找到，不一定是最左的位置，从左半区再找一次
                    right = i - 1;
                }
            }
        }
        return res;
    }
}
