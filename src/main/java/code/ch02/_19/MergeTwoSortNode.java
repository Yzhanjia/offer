package code.ch02._19;

import code.ch02.Node;

/**
 * 合并两个有序链表
 */
public class MergeTwoSortNode {
    /**
     * @param head1
     * @param head2
     * @return
     */
    public static Node merge(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;//若其中一节点为空，则直接返回另一节点
        }
        Node head = head1.value <= head2.value ? head1 : head2;//要返回的头结点即两条链表中的最小节点
        Node cur1 = head == head1 ? head1 : head2;//cur1为主链表
        Node cur2 = head == head1 ? head2 : head1;//cur2为要插入的链表
        Node pre = null;//上次比较最小值
        Node next = null;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre = cur1;
                cur1 = cur1.next;//直接进入下一节点
            } else {
                next = cur2.next;//记录下一节点
                pre.next = cur2;
                cur2.next = cur1;//这两步将cur2节点插入主链表
                pre = cur2;//记录上一最小节点
                cur2 = next;//移入下一节点
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;//其中一链表遍历完，直接将另一链表的剩余节点接到最后

        return head;
    }
}
