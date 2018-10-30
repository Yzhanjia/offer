package code.ch02._15;

import java.util.LinkedList;
import java.util.Queue;

/**
 *    将搜索二叉树转化为双向链表
 */
public class ConvertNode {
    //使用队列，中序遍历
    public static Node convert1(Node head) {
        Queue<Node> queue = new LinkedList<>();
        inOnderToQueue(head, queue);
        if (queue.isEmpty()) {
            return head;
        }
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    private static void inOnderToQueue(Node head, Queue<Node> queue) {//中序遍历
        if (head == null) {
            return;
        }
        inOnderToQueue(head.left, queue);
        queue.offer(head);
        inOnderToQueue(head.right, queue);
    }

    //利用递归函数，不使用额外的容器
    public static Node revert2(Node head) {
        if (head == null) {
            return null;
        }
        Node last = process(head);
        head = last.right;
        last.right = null;
        return head;
    }

    private static Node process(Node head) {
        if (head == null) {
            return null;
        }
        Node leftE = process(head.left);//左右两部分分开处理，左边的尾节点
        Node rightE = process(head.right);
        Node leftS = leftE != null ? leftE.right : null;//左边的头结点
        Node rightS = rightE != null ? rightE.right : null;
        if (leftE != null && rightE != null) {//将左右两部分连接到头结点
            leftE.right = head;
            head.left = leftE;
            head.right = rightE;
            rightS.left = head;
            rightE.right = leftS;
            return rightE;
        } else if (leftE != null) {
            leftE.right = rightS;
            head.left = leftE;
            head.right = leftS;
            return head;
        } else if (rightE != null) {
            head.right = rightS;
            rightS.left = head;
            rightE.right = head;
            return rightE;
        } else {
            head.right = head;
            return head;
        }
    }
}
