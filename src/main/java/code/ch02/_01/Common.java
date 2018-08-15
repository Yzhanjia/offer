package code.ch02._01;

import code.ch02.Node;

/**
 * 打印两个有序链表的公共部分
 */

public class Common {

    public void printCommonPart(Node head1, Node head2) {
        System.out.println("Common part: ");
        while (head1 != null && head2 != null) {
            //移动较小链表
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {//值相等的部分
                System.out.println(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }
}
