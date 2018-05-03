package leetcode;
/*
请检查一个链表是否为回文链表。

进阶：
你能在 O(n) 的时间和 O(1) 的额外空间中做到吗？
 */

import java.util.Stack;

/*
时间O(n) 空间(n) 栈实现
 */
public class PalindromeList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {//空值为回文链表
            return true;
        }
        if (head.next == null) {//只有一个节点
            return true;
        }
        ListNode node = head;
        int count = 0;//计算节点个数
        Stack<ListNode> stack = new Stack();//用栈存储，最顶为最后一个节点
        while (node != null) {
            stack.push(node);
            count++;
            node = node.next;
        }
        int i = 0;
        while (i < count / 2 && head.val == stack.peek().val) {//遍历一半节点，逐一比较
            head = head.next;
            stack.pop();//若相同，出栈，比较下一节点
            i++;
            if (i >= count / 2 || stack.isEmpty()) {//栈为空或已比较一半节点
                return true;
            }
        }
        return false;

    }

    /*
    反转后半部分链表
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode n1 = head;
        ListNode n2 = head;
        boolean flag = true;
        while (n2.next != null && n2.next.next != null) {
            n2 = n2.next.next;//找到中间节点
            n1 = n1.next;
        }
        ListNode n3;
        n2 = n1.next;//保存中间节点的下一节点
        n1.next = null;//将中间节点的下一节点置为空
        while (n2 != null) {
            n3 = n2.next;//保存中间节点的下一节点的下一节点
            n2.next = n1;//反转两个节点
            n1 = n2;
            n2 = n3;//两个指针均移动到下一节点
        }
        n3 = n1;//保存尾节点
        n2 = head;//从头结点开始比较
        while (n1.next != null) {
            if (n1.val == n2.val) {
                n1 = n1.next;
                n2 = n2.next;
            } else {
                flag = false;
                break;//不相同，不是回文链表跳出循环，不再比较
            }
        }
        //不管结果如何均还原节点
        n2 = n3.next;
        n3.next = null;//设置尾节点的下一节点为空
        while (n2 != null) {
           n1 = n2.next;//保存上一节点
           n2.next = n3;//反转两个节点
           n3 = n2;
           n2 = n1;//移动两个节点到下一节点
        }
        return flag;

    }

}


