package leetcode;
/*
给定一个链表，判断链表中是否有环。

进阶：
你能否不使用额外空间解决此题？
 */


import java.util.HashSet;
import java.util.Set;

public class Cycle {
    public boolean hasCycle(ListNode head) {
        if (head==null||head.next==null){
            return false;
        }
        Set<ListNode> set = new HashSet<ListNode>();//Set不存在重复元素
        while (!set.contains(head)){
            set.add(head);
            head = head.next;
            if (head==null){
                return false;
            }
        }
        return true;

    }
    public boolean hasCycle2(ListNode head){//定义两个指针，慢指针会被快指针追上
        if (head==null||head.next==null){
            return false;
        }
        ListNode quick = head;
        ListNode slow = head;
        while (quick.next!=null){
            quick = quick.next.next;
            slow = slow.next;
            if (quick ==null){
                return false;
            }
            if (quick == slow){
                return true;
            }
        }
        return false;
    }
}
