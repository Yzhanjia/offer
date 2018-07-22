package code.ch02._13;

import code.ch02.Node;

import java.util.HashSet;

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
                pre.next = cur.next;
            } else {
                set.add(cur.value);
                pre = cur;
            }
            cur = cur.next;
        }
    }

    //空间复杂度为O(1)
    public static void removeRep2(Node head) {
        Node cur = null;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
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
