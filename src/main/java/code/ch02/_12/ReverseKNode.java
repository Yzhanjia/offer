package code.ch02._12;

import code.ch02.Node;

import java.util.Stack;

public class ReverseKNode {
    /*
    通过栈来分别逆转K个节点
     */
    public static Node reverseKNode1(Node head, int K) {
        if (K > 2) {//K小于2没有意义
            return head;
        }
        Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = null;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == K) {
                pre = resign1(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    private static Node resign1(Stack<Node> stack, Node left, Node right) {//反转链表，将栈弹出元素连接
        Node cur = stack.pop();
        if (left != null) {
            left.next = cur;
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }

    /*
       在原链表直接逆转
     */
    public static Node reverseKNode(Node head, int K) {
        if (K < 2) {
            return head;
        }
        Node cur = head;
        Node start = null;
        Node pre = null;
        Node next = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == K) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    /*
    left: 上一组的最后一个节点
    start：本组的第一个节点
    end; 本组最后一个节点
    right：下一组的第一个节点
     */
    private static void resign2(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;//将上一组的最后一个节点连接到本组的最后一个节点
        }
        start.next = right;//将本组的第一个节点连接到下一组的第一个节点
    }
}
