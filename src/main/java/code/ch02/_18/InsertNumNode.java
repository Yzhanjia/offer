package code.ch02._18;

import code.ch02.Node;

/*
  给定升序的环形单链表，生成值为num的节点插入链表，使插入后的链表依旧有序
 */
public class InsertNumNode {
    public static Node insertNode(Node head, int num) {
        Node node = new Node(num);
        if (head == null) {
            node.next = node;
            return node;//若链表为空，当前节点即为所求的头结点
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head) {
            if (pre.value <= num && cur.value >= num) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;//若遍历节点没有满足，则生成节点比原来所有节点都小或都大
        node.next = cur;
        return head.value < num ? head : node;//若生成的节点为最小节点，则生成的节点为新的头结点
    }
}

