package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
给定两个数组，写一个方法来计算它们的交集。

例如:
给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].

注意：

   输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
   我们可以不考虑输出结果的顺序。
跟进:

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果nums2的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Intersect {

    public static void main(String[] args) {
        int[] nums1 = {4,7,9,7,6,7};
        int[] nums2 = {5,0,0,6,1,6,2,2,4};
        intersect(nums1,nums2);
    }
    /*
    先将数组排序的做法
     */
    //        public int[] intersect(int[] nums1, int[] nums2) {
//         Arrays.sort(nums1);
//         Arrays.sort(nums2);
//         List<Integer> temp = new ArrayList<Integer>();
//         int i = 0;
//         int j = 0;
//         while (i<nums1.length&&j<nums2.length){
//             if (nums1[i] == nums2[j]){
//                 temp.add(nums1[i]);
//                 i++;
//                 j++;
//             }else if (nums1[i]<nums2[j]){
//                 i++;
//             }else {
//                 j++;
//             }

//         }
//         int [] res = new int[temp.size()];
//         for (int k = 0; k < res.length ; k++) {
//             res[k] = temp.get(k);
//         }
//         return res;

    //     }
    /*
    不排序的做法
     */
    public static int[] intersect(int[] nums1, int[] nums2){
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();//存储num1的元素和出现次数
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])){
                int j = map.get(nums1[i]) + 1;
                map.put(nums1[i],Integer.valueOf(j));
            }else {
                map.put(nums1[i],1);
            }
        }
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < nums2.length; i++) {
            if (!map.containsKey(nums2[i])){//如果在map中没有当前元素，跳出一次循环
                continue;
            }else {
                if (map.get(nums2[i])==0){//map次数为0，说明nums1中的元素已遍历完
                   continue;
                }
                list.add(nums2[i]);//将当前元素添加到列表中
                int j = map.get(nums2[i]) - 1;//对应元素次数减一
                map.put(nums2[i],Integer.valueOf(j));
            }
        }
        int[] res = new int[list.size()];//将列表转换为数组
        for (int i = 0; i <res.length ; i++) {
            res[i] = list.get(i);
        }

        return res;

    }

}
