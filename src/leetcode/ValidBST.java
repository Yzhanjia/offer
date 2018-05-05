package leetcode;
/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
 */

public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        //long保存，int保存会除去边界值
        return isValidBST(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }

    private boolean isValidBST(TreeNode root,long Max,long Min) {
       if (root==null){//遍历的终点：没有子节点
           return true;
       }
       //若小于节点小于左子节点或大于右子节点，不符合二叉搜索树
       if (root.val<=Min||root.val>=Max){
           return false;
       }
       //左子节点小于当前节点，保存上一大值和小值为了保证子树符合二叉搜索树的规律
       return isValidBST(root.left,root.val,Min)&&
               //右子节点大于当前节点
               isValidBST(root.right, Max, root.val);
    }
}
