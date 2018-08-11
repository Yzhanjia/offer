package code.ch03._07;

import code.ch03.Node;

/**
 * 找到二叉树中的最大搜索二叉树
 */
public class BiggestBST {
    public static Node biggestSubBST(Node head) {
        int[] record = new int[3];
        return posOrder(head, record);
    }

    private static Node posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        //后序遍历二叉树找到最大的搜索二叉树
        //先从左子树开始
        Node lBST = posOrder(left, record);//左子树中的最大搜索二叉树
        int lSize = record[0];//搜索二叉树的大小
        int lMin = record[1];//搜索二叉树的最小值
        int lMax = record[2];//搜索二叉树的最大值
        //再遍历右子树
        Node rBST = posOrder(right, record);//右子树的最大搜索二叉树
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        //搜索二叉树的头节点的左子树的最大节点小于头节点
        //右子树的最小节点大于头节点
        record[1] = Math.min(lMin, value);//记录左子树的最小值
        record[2] = Math.max(rMax, value);//记录右子树的最大值
        //满足搜索二叉树的条件，返回该搜索二叉树的头节点
        // 左子节点和右子节点均为搜索二叉树的头结点，并且头结点也符合搜索二叉树的性质
        //整颗树为搜索二叉树
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;//记录搜索二叉树的大小
            return head;
        }
        //整棵树不满足搜索二叉树，从左右子树中寻找
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;//返回左右子树中最大搜索二叉树的头节点

    }

}
