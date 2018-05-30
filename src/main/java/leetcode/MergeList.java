package leetcode;
/*
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */

public class MergeList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head;
        if (l1 == null){//其中一条为空，即为另一条
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        if (l1.val<l2.val){
            head = l1;//合并
            head.next = mergeTwoLists(l1.next,l2);//l1的剩余节点和l2
        }else {
            head = l2;
            head.next = mergeTwoLists(l1,l2.next);
        }
        return  head;

    }
}
