package leetcode;

public class Depth {
    /*
    给定一个二叉树，找出其最大深度。

    二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

    说明: 叶子节点是指没有子节点的节点。

    给定二叉树 [3,9,20,null,null,15,7]

        3
       / \
      9  20
        /  \
       15   7
    返回它的最大深度 3 。
     */

    public int maxDepth(TreeNode root) {
        if (root==null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return (left>right)? (left+1):(right+1);//如果只有左子树没有右子树，树的深度为左子树深度+1
        //若有左右子树，则为左右子树深度的较大值


    }
}
