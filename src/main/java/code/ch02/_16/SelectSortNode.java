package code.ch02._16;

import code.ch02.Node;

/*.
   空间复杂度为O(1),对未排序的链表进行选择排序
 */
public class SelectSortNode {
    //逐个找到最小的节点放至尾节点，实现从小到大排序
    public static Node selectionSort(Node head) {
        Node tail = null;//排序部分的尾部
        Node cur = head;//未排序部分的头部
        Node smallPre = null;//最小节点的前一个节点
        Node small = null;//最小的节点
        while (cur != null) {
            small = cur;
            smallPre = getSmallestPreNode(cur);
            if (smallPre != null) {//删除原来链表找出的最小的节点
                small = smallPre.next;
                smallPre.next = small.next;
            }
            //若cur==small,证明当前节点为最小节点，即smallPre为null,cur = cur.next,即删除当前的头结点
            // 若cur!=small,最小节点不为当前节点
            //继续再剩下的节点寻找
            cur = cur == small ? cur.next : cur;
            if (tail == null) {
                head = small;//tail为null,即找到第一个最小节点，作为头结点
            } else {
                tail.next = small;//逐个连接起来
            }
            tail = small;//将剩余最小节点作为尾节点
        }
        return head;
    }

    private static Node getSmallestPreNode(Node head) {
        Node smallPre = null;
        Node small = null;
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.value < small.value) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }
}
