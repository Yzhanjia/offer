package code.ch02._13;

import code.ch02.Node;

import java.util.HashSet;

/**
 * 删除无序单链表中值重复出现的节点
 */
public class RemoveRepeat {
    //利用哈希表，时间复杂度O（N）
    public static void removeRep1(Node head) {
        if (head == null) {
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        Node pre = head;
        Node cur = head.next;
        set.add(head.value);
        while (cur != null) {
            if (set.contains(cur.value)) {
                pre.next = cur.next;//哈希表已有则删除该节点
            } else {
                set.add(cur.value);//未出现则将当前节点加入哈希表
                pre = cur;//移动指针，保持指向当前节点的上一节点
            }
            cur = cur.next;
        }
    }

    //空间复杂度为O(1)，时间复杂度为O(N^2),类似选择排序
    public static void removeRep2(Node head) {
        Node cur = null;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
                //逐个删除与当前节点值相同的节点
                if (cur.value == next.value) {
                    pre.next = cur.next;
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }
    }
}
