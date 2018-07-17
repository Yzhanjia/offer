package code.ch02._06;

import code.ch02.Node;

public class Josephus {
    public Node josephusKill1(Node head, int m) {
        Node last = head;
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = head.next;
        }
        return head;
    }

    public Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1;
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m);
        while (--tmp != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    private int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - i) % i + 1;
    }
}
