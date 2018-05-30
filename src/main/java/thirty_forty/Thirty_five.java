package thirty_forty;

public class Thirty_five {
    public static ComplexListNode cloneNode(ComplexListNode head){
        if (head==null){
            return null;
        }
        cloneNodes(head);
        connectSiblingNodes(head);
        return reConnectNodes(head);
    }

    private static ComplexListNode reConnectNodes(ComplexListNode head) {
        if (head==null){
            return null;
        }
        ComplexListNode newHead = head.next;
        ComplexListNode pointer = newHead;
        head.next= newHead.next;
        head = head.next;
        while (head!=null){
            pointer.next = head.next;
            pointer = pointer.next;
            head.next = pointer.next;
            head = head.next;
        }
        return newHead;
    }

    private static void connectSiblingNodes(ComplexListNode head) {
        while (head!=null){
            if (head.sibling!=null){
                head.next.sibling = head.sibling.next;
            }
            head = head.next.next;
        }
    }

    private static void cloneNodes(ComplexListNode head) {
//        ComplexListNode temp = new ComplexListNode();
      while (head!=null){
             ComplexListNode temp = new ComplexListNode();
             temp.value = head.value;
             temp.next = head.next;
             head.next = temp;
             head = temp.next;
        }
    }

    public static void printList(ComplexListNode head){
        while (head!=null){
            System.out.println(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public  static boolean isSame(ComplexListNode h1,ComplexListNode h2){
        while (h1!=null&&h2!=null){
            if (h1.value==h2.value){
                h1 = h1.next;
                h2 = h2.next;
            }else {
                return false;
            }
        }
        return h1==null && h2 ==null;
    }
}
