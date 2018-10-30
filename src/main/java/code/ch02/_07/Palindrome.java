package code.ch02._07;

import code.ch02.Node;

import java.util.Stack;

/**
 * 判断一个链表是否为回文结构
 */
public class Palindrome {
    /**
     * 直接用栈结构，头尾比较
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = null;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 只压入一半节点，即分成左右两部分，左右头尾比较
     * @param head
     * @return
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next;
        Node cur = head.next.next;
        //找到中间节点的下一节点，即右半部分的第一个节点
        if (cur.next != null || cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        //将右半部分全部压入栈
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        //逐个比较
        while (stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 不需栈结构，反转后半部分，头结点和尾节点同时移动比较
     * @param head
     * @return
     */
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null || n2.next.next != null) {//找中间节点
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        /*
        反转后半部分的链表
         */
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;//记录最后一个节点
        n2 = head;//从左边开始遍历
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        //将链表还原
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
