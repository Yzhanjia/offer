package code.ch02._06;

import code.ch02.Node;

/**
 * 环形单链表的约瑟夫问题
 * 非定环形单链表的头结点和报数值
 * 返回最后生存的节点
 * 进阶：
 * O(N)时间复杂度完成
 */
public class Josephus {
    /**
     * 普通解法不断转圈不断删除，时间复杂度为O（n*m）
     * 由于不知道哪个节点会留下来，只通过不断删除直到只剩一个节点
     *
     * @param head
     * @param m
     * @return
     */
    public Node josephusKill1(Node head, int m) {
        Node last = head;
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        while (last.next != head) {
            last = last.next;//找到头结点的上一节点，遍历链表时便于删除节点
        }
        int count = 0;
        while (head != last) {//直到只剩一个节点
            if (++count == m) {//不断报数，直到找到第m个节点
                last.next = head.next;//删除当前节点head
                count = 0;//重新报数
            } else {
                last = last.next;//从头开始报数
            }
            head = head.next;
        }
        return head;
    }

    /**
     * 直接算出哪个节点会生存下来
     * @param head
     * @param m
     * @return
     */
    public Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1;
        while (cur != head) {
            tmp++;
            cur = cur.next;//求链表的节点数
        }
        tmp = getLive(tmp, m);
        while (--tmp != 0) {
            head = head.next;//找到最后生存下来的节点
        }
        head.next = head;//将生存的节点自身成环
        return head;
    }

    /**
     * 找出Num(i - 1) 与Num(i）的关系
     * 1.报数A与当前编号B的关系：B = （A - 1） % i + 1 // i当前节点数
     * 2.删除前节点的编号与删后的节点编号的位置 old = （new + s -1）% i +1 // s为删除的节点编号
     * 代入得：old = (new + m - 1 ) % i + 1;
     * @param i 链表节点数
     * @param m 要报的数
     * @return 最后要删除的节点的位置
     */
    private int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - i) % i + 1;
    }
}
