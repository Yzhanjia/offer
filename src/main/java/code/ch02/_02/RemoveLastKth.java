package code.ch02._02;


import code.ch02.DoubleNode;
import code.ch02.Node;

/**
 * 在单链表和双链表中删除倒数第k个节点
 */
public class RemoveLastKth {
    public Node removeLastKthNode(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        Node cur = head;
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }
        if (lastKth == 0) {//删除头结点
            head = head.next;
        }
//        if (lastKth > 0) {
//            return head;
//        }
        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {//当lastKth为0时，找到要删除节点的前一个节点
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        //k大于链表的节点数，不需调整直接返回头结点
        return head;
    }

    public DoubleNode removeLastKthNode(DoubleNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        DoubleNode cur = head;
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }
        if (lastKth == 0) {
            head = head.next;
            head.last = null;//多一步将指向上一节点设为NULL
        }
        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            DoubleNode newNext = cur.next.next;
            cur.next = newNext;
            if (newNext != null) {
                newNext.last = cur;//多一步
            }
        }
        return head;

    }
}
