package code.ch02._20;

import code.ch02.Node;

/**
 * 将链表分为左右两部分，一左一右重新连接成新的链表
 */
public class MergeLAndR {
    /**
     * @param head
     */
    public static void relocate(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = head;
        Node right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next;//找到中间节点
        }
        right = mid.next;
        mergeLR(head, right);
    }

    private static void mergeLR(Node left, Node right) {
        Node next = null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;//左节点
            right = next;
        }
        left.next = right;
    }
}
