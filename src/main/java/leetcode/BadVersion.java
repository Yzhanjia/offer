package leetcode;
/*
你是产品经理，目前正在领导一个团队开发一个新产品。不幸的是，
您的产品的最新版本没有通过质量检查。由于每个版本都是基于之前的版本开发的，
所以错误版本之后的所有版本都是不好的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出第一个错误的版本，导致下面所有的错误。

你可以通过 bool isBadVersion(version) 的接口来判断版本号 version 是否在单元测试中出错。
实现一个函数来查找第一个错误的版本。您应该尽量减少对 API 的调用次数。
 */

public class BadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        if (n <= 0) return 0;
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;//mid = (r + l)/2，可能越界
            if (isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
//        /*
//        二分法求解
//         */
//        int start = 0;
//        int end = 0;
//        while (start + 1 <end){//数组长度大于2
//            int middle = start + (end - start)/2;
//            if (isBadVersion(middle)){
//                end = middle;
//            }else {
//                start = middle;
//            }
//        }
//        //数组长度只有一个或两个
//        if (isBadVersion(end)){
//            return end;
//        }else {
//            return start;
//        }
//
//
//    }
    }

}
