package code.ch02._04;

import code.ch02.DoubleNode;
import code.ch02.Node;

/**
 * 反转单向和双向链表
 */
public class ReverseNodeList {
    /**
     * 反转单向链表
     *
     * @param head
     * @return
     */
    public Node reverseList(Node head) {
        Node pre = null;//最开始相当于链表最后指向null
        Node next = null;
        while (head != null) {
            next = head.next;//记录下一节点
            head.next = pre;//将当前节点的指针指向上一节点，反转步骤
            pre = head;//移动pre指针，使pre指向当前节点
            head = next;//再将当前指针指向下一节点，进入下一循环
        }
        return pre;
    }

    /**
     * 反转双向链表
     * @param head
     * @return
     */
    public DoubleNode reverseList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;//比单链表多这一步，使当前链表的上一节点转为下一节点
            pre = head;
            head = next;
        }
        return pre;
    }
}
