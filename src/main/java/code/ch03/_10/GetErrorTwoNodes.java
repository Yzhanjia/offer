package code.ch03._10;

import code.ch03.Node;

import java.util.Stack;

/**
 * 1.二叉搜索树的其中两个节点调换位置，找到这两个节点并返回
 * 2.找到这两个节点后，调换这两个节点的位置，不是简单交换节点值
 */
public class GetErrorTwoNodes {
    /**
     * 中序查找并返回
     *
     * @param head
     * @return
     */
    public static Node[] getTwoErrorNodes(Node head) {
        Node[] errs = new Node[2];
        if (head == null) {
            return errs;
        }
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.value > head.value) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return errs;
    }

    /**
     * 获取错误节点的父节点
     * 使其成为各自的子节点
     *
     * @param head
     * @return
     */
    public static Node reverseTree(Node head) {
        Node[] errs = getTwoErrorNodes(head);
        Node[] parents = getErrNodes(head, errs[0], errs[1]);
        Node e1 = errs[0];
        Node e1P = parents[0];
        Node e1L = e1.left;
        Node e1R = e1.right;
        Node e2 = errs[1];
        Node e2P = parents[1];
        Node e2L = e2.left;
        Node e2R = e2.right;
        /**
         * 问题一：其中之一是否为根节点，有是哪一个
         * 问题二：e1.e2是否相邻，谁是父节点
         * 问题三：e1,e2分别是各自父节点的左孩子还是右孩子
         */
        if (e1 == head) {//e1为头结点，即e1P为null
            if (e1 == e2P) {//节点1是节点2的父节点，即节点2为节点1的有孩子 1
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1;
                e2.left = e1L;
            } else if (e2P.left == e2) {//2
                e2P.left = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            } else {//3
                e2P.right = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e2;
        } else if (e2 == head) {//4
            if (e2 == e1P) {
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            } else if (e1P.left == e1) {//5
                e1P.left = e2;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            } else {//6
                e1P.right = e2;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e1;
        } else {
            if (e1 == e2P) {//e1为头结点，即e1P为null
                if (e1P.left == e1) {//节点1是节点2的父节点，即节点2为节点1的有孩子 //7
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.right = e1;
                    e2.left = e1L;
                } else {//8
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }
            } else if (e2 == e1P) {
                if (e2P.left == e2) {//9
                    e2P.left = e1;
                    e2.right = e1R;
                    e2.left = e1L;
                    e1.left = e2;
                    e1.right = e2R;
                } else {//10
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
                e1.right = e2R;

            } else {
                if (e1P.left == e1) {
                    if (e2P.left == e2) {//11
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e2P.left = e1;
                        e1P.left = e2;

                    } else {//12
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e2P.right = e1;
                        e1P.left = e2;
                    }
                } else {
                    if (e2P.left == e2) {//13
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e2P.left = e1;
                        e1P.right = e2;

                    } else {//14
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e2P.right = e1;
                        e1P.right = e2;
                    }
                }
            }
        }
        return head;
    }

    private static Node[] getErrNodes(Node head, Node e1, Node e2) {
        Node[] parents = new Node[2];
        if (head == null) {
            return parents;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.left == e1 || head.right == e1) {
                    parents[0] = head;
                }
                if (head.right == e2 || head.right == e2) {
                    parents[1] = e2;
                }

            }

        }
        return parents;
    }
}
