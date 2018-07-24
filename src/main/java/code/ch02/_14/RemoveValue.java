package code.ch02._14;

import code.ch02.Node;

import java.util.Stack;

/*
删除所有为指定值得节点
 */
public class RemoveValue {
    //利用栈
    public static Node removeValue1(Node head, int num) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            if (head.value != num) {
                stack.push(head);
            }
            head = head.next;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;//head一开始为null
            head = stack.pop();//重新连接链表
        }
        return head;
    }

    //时间复杂度为O(N)
    public static Node removeValue2(Node head, int num) {
        while (head != null) {
            if (head.value != num) {//找到第一个值不为num的节点作为新的头结点
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;

    }
}
