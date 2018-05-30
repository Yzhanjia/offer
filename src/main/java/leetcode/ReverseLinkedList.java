package leetcode;

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null){//如果节点数小于2，则直接返回该节点，包括null
            return head;
        }
        /*
       定义三个指针，分别指向前一节点，当前节点，后一节点。
        */
        ListNode p,q,r ;
        p = head;
        q = head.next;
        head.next = null;
        while (q!=null){
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        //若当前节点已为空值，则上一节点为反转链表的头结点
        head = p;
        return head;
    }
    /*
     递归实现
     */
    public ListNode reverseList2(ListNode head){
        if (head==null){//如果当前节点为空，返回NULL
            return null;
        }
        if (head.next==null){
            return head;//如果下一节点为null，证明已达到链表的尾节点，返回当前节点，即为反转链表的头结点
        }
        ListNode next = head.next;//记录下一节点
        ListNode rest = reverseList2(next);//递归实现除去头结点后，链表的剩余节点，直到尾节点
        next.next = head;//反转两个节点
        head.next = null;//设置尾节点
        return rest;//返回翻转后的头结点

    }

}
