package leetcode;

import designPattern.abstractFactory.PcFactory;

public class RemoveNth {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        removeNthFromEnd(head,1);
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //前提为N有效，无此前提，需判断所给节点是否为空和N是否为0,且在循环中，需判断n是否大于节点个数
      ListNode pre = head;
      ListNode cur = head;
        for (int i = 0; i < n; i++) {
            cur = cur.next;//移动一个指针移动n步,保持后一节点在前一节点后的第n个，求出所要删除节点的前一节点
            //若所要求为倒数第N个节点，则后一节点
        }
        if (cur==null){//如果先移动的指针指向节点为空，证明所要删除的节点为头结点
            return pre.next;
        }
        while (cur.next!=null){
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = pre.next.next;//删除下一节点即为所要删除节点

        return head;

    }
}
