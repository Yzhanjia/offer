package code.ch03._05;

import code.ch03.Node;

/**
 * 实现二叉树的先序，中序，后序遍历
 * 要求空间复杂度为O(1)
 * 即不使用递归和栈实现遍历
 */
public class Morris {
    /**
     * 中序遍历
     */
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        /*
         将叶子节点的右节点指向父节点
         */
        while (cur1 != null) {//遍历到cur1为null时，移动到该二叉树的最右节点，结束
            cur2 = cur1.left;
            if (cur2 != null) {
                //找到左子树的最右节点
                //将左子树的最右节点的右指针指向该父节点
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;//将左子树的最右节点的右指针指向头结点
                    cur1 = cur1.left;//移动到接下来的左子树
                    continue;
                } else {
                    cur2.right = null;//将cur2.right == cur1,从下往上逐渐恢复为右节点为null的模式
                }
            }
            //当cur2为null时，cur1为左子树的最左节点
            System.out.println(cur1.value + " ");//从左到右打印
            //如果该节点无右子节点，通过cur1.right移动到父节点
            //如果该节点有右子节点，通过cur1.right移动到右子节点
            cur1 = cur1.right;
        }
        System.out.println();
    }

    /**
     * 先序遍历
     */
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    System.out.println(cur1.value + " ");//与中序遍历不同的地方，在这里打印,依次打印各个左边界
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;//恢复为原来的状态
                }

            } else {
                System.out.println(cur1.value + " ");//遍历到叶子节点，打印
            }
            cur1 = cur1.right;//与中序遍历相同
        }
        System.out.println();
    }

    /**
     * 后序遍历
     */
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {//最先打印最左节点，然后从左往右由下往上依次打印右边界
                    cur2.right = null;
                    printEdge(cur1.left);//依次打印每个节点的左子树的右边界
                }
            }
            //如果cur1.right原先为null,则通过右指针回到父节点
            //如果有右子节点，则通过右指针移到右子节点
            cur1 = cur1.right;
        }
        //因为整棵树不属于任何树的左子树，整棵树的右边界最后统一打印
        printEdge(head);
        System.out.println();
    }

    /*
    打印边界
     */
    private static void printEdge(Node head) {
        Node tail = reverseEdge(head);//先反转右边界，使能从最右节点由下往上打印
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);//恢复原来的状态
    }

    /*
    反转右边界，使最底层的最右节点能找到父节点
     */
    private static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }
}

