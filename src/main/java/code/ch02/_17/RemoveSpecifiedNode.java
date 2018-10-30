package code.ch02._17;

import code.ch02.Node;

/**
 *    给定一个节点，不给头结点，删除该节点
 */
public class RemoveSpecifiedNode {
    //将node.value = node.next.value, 该方法无法删除尾节点
    public static void removeNodeWired(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        if (next == null) {
            throw new RuntimeException("can not remove last node");
        }
        node.value = next.value;
        node.next = next.next;


    }
}
