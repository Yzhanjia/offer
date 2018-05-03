package leetcode;

public class DeleteNode {
    public void deleteNode(ListNode node) {
//        if (node.next==null){
//            node = null;
//        }
//        while (node.next!=null){
//            node.val = node.next.val;
//            ListNode tmp = node;
//            node = node.next;
//            if (node.next==null){
//                tmp.next = null;
//                break;
//            }
//        }
        node.val = node.next.val;
        node.next = node.next.next;

    }
}
