package code.ch03._13;

import code.ch03.Node;

/**
 * 判断二叉树是否为平衡二叉树
 * 平衡二叉树，左子树的高度与右子树的高度差不超过1
 */
public class IsBalanceTree {
    public static boolean isBalance(Node head) {
        boolean[] res = new boolean[1];//作为全局变量
        res[0] = true;
        getHeight(head, 1, res);
        return res[0];
    }

    /**
     * 后序遍历二叉树
     *
     * @param head  判断的节点
     * @param level 当前高度
     * @param res   作为全局变量，判断是否为平衡二叉树
     * @return
     */
    private static int getHeight(Node head, int level, boolean[] res) {
        if (head == null) {
            return level;
        }
        int lH = getHeight(head.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rH = getHeight(head.right, level + 1, res);
        if (!res[0]) {
            return level;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }
        return Math.max(lH, rH);
    }
}
