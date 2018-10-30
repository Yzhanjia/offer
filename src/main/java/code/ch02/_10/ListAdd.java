package code.ch02._10;

import code.ch02.Node;

import java.util.Stack;

/**
 * 链表每个节点代表一位数，整个链表代表一个整数
 * 返回代表两个链表相加的和的新链表
 */
public class ListAdd {
    /**
     * @param head1
     * @param head2
     * @return
     */
    public Node addList1(Node head1, Node head2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (head1 != null) {
            s1.push(head1.value);
            head1 = head1.next;
        }
        while (head2 != null) {
            s2.push(head2.value);
            head2 = head2.next;
        }
        int ca = 0;//进位
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node node = null;
        Node pre = null;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            n1 = s1.isEmpty() ? 0 : s1.pop();
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;//模拟进位相加，当前位数分别相加再加上上一位的相加之和的进位
            pre = node;//记录当前节点
            node = new Node(n % 10);//新节点为两个数相加的个位部分
            node.next = pre;
            ca = n / 10;
        }
        //最后还有一个节点，再生成一个新节点
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;

    }

    /**
     * 逆序，节省栈的空间
     * 反转链表可以从尾部开始相加
     * @param head1
     * @param head2
     * @return
     */
    public static Node addList2(Node head1, Node head2) {
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node c1 = head1;
        Node c2 = head2;
        Node node = null;
        Node pre = null;
        //同方法一
        while (c1 != null || c2 != null) {
            n1 = c1 == null ? 0 : c1.value;
            n2 = c2 == null ? 0 : c2.value;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 == null ? null : c1.next;
            c2 = c2 == null ? null : c2.next;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        //最后在反转回来
        reverseList(head1);
        reverseList(head2);
        return node;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


}
