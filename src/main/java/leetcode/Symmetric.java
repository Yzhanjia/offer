package leetcode;
/*
给定一个二叉树，检查它是否是镜像对称的。
 */

public class Symmetric {
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root,root);

    }

    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1==null&&root2==null){//两个空节点，相同
            return true;
        }
        if (root1==null||root2==null){//只有一个空节点，不对称
            return false;
        }
        if (root1.val!=root2.val){//值不相同，不对称
            return false;
        }//对称遍历
        return isSymmetric(root1.left,root2.right)&&isSymmetric(root1.right,root2.left);
    }
}
