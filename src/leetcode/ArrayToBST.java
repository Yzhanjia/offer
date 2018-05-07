package leetcode;
/*
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
 */

public class ArrayToBST {
    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        sortedArrayToBST(nums);
    }
/*
利用二分法，找出根节点
 */
    public static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = null;
        if (nums.length == 0) {
            return root;//数组为空，返回空值
        }
        int start = 0;
        int end = nums.length - 1;
        return sortedArrayToBST(nums, start, end);

    }

    private static TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (start + end) / 2;//二分法搜索中值
        TreeNode node = new TreeNode(nums[middle]);
        node.left = sortedArrayToBST(nums,start,middle-1);//中值的左半部分
        node.right = sortedArrayToBST(nums,middle+1,end);//中值的右半部分
        return node;

    }
}
