package code.ch02._11;

import code.ch02.Node;

/**
 * 两个链表相交，求橡胶的第一个节点，否则返回null
 */
public class Intersect {
    /**
     * 获取第一个相交结点，两条链表都可能为环
     * @param head1
     * @param head2
     * @return
     */
    public static Node getInterectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {//两条链表都无环
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {//两条链表都有环
            return bothLoop(head1, loop1, head2, loop2);
        }
        //若只有一条链表有环则不可能相交
        return null;
    }

    /*
        如果两链表都有环，判断相交情况
     */
    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {//两条链表入环节点相同
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {//将入环节点作为无环部分的终点
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head2 ? head1 : head2;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//若两个链表的入环节点不同，则相交节点为环
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {//遍历环节点没有遇到另一链表的入环节点，则两个环不是同一个环，即两链表不相交
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;

        }
    }

    /*
        如果两链表都无环，判断相交情况
     */
    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;

        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;//求两条链表的节点个数差
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null; //无环链表相交最后部分肯定相同
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {//较长的链表先移动两个链表的个数差个节点
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {//当两指针相遇时，为相交的节点
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /*
      获取入环第一个节点
     */
    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;//0-2个节点不可能成环
        }
        Node n1 = head.next;//slow指针
        Node n2 = head.next.next;//fast指针
        while (n1 != n2) {//当两个节点相遇时，快指针比慢指针多走了环的个数个节点
            if (n2.next == null || n2.next.next == null) {
                return null; //链表无环
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = head;
        while (n1 != n2) {//当两个节点相遇时，为入环的第一个节点，两个节点之间的距离为环的个数个节点
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }
}
