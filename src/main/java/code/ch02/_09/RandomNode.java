package code.ch02._09;

import java.util.HashMap;

/**
 * 复制含有随机指针节点的链表
 */
public class RandomNode {
    /**
     * 普通解法： 时间复杂度为O(N) 空间复杂度为O(N)
     *
     * @param head
     * @return
     */
    public Node copyListWithRand1(Node head) {
        //键为原节点，值为复制的新节点
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        //逐个复制节点
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        //根据原链表连接复制链表
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 原节点的下一节点指向副本节点
     * @param head
     * @return
     */
    public Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        //连接随机指针部分
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        //剥离两个链表
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;

    }
}
