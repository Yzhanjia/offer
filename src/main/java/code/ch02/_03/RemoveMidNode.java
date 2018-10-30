package code.ch02._03;

import code.ch02.Node;

/**
 * 删除链表的中间节点和a/b处的节点
 */
public class RemoveMidNode {
    public Node removeMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;//只有两个节点，删除头结点
        }
        Node pre = head;
        Node cur = head.next.next;//两个next是为了找到中间节点的上一节点
        while (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;//cur的移动速度是pre的两倍
        }
        pre.next = pre.next.next;//删除中间节点
        return head;
    }

    /**
     * 指定比例删除目标节点
     *
     * @param head
     * @param a
     * @param b
     * @return
     */
    public Node removeByRatio(Node head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }
        int n = 0;
        Node cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;//记录节点数
        }
        n = (int) Math.ceil((double) (a * n) / (double) b);//向上取整，求要删除的节点位置
        if (n == 1) {
            head = head.next;
        }
        if (n > 1) {
            cur = head;
            while (--n != 1) {//找到目标节点的上一节点
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
}
