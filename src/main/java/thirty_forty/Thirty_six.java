package thirty_forty;

public class Thirty_six {

    public static BinaryTreeNode convert(BinaryTreeNode root){
        BinaryTreeNode[] lastNode = new BinaryTreeNode[1];
        convertNode(root,lastNode);
        BinaryTreeNode head = lastNode[0];
        while (head.left!=null&&head!=null){
            head = head.left;
        }
        return head;
    }

    private static void convertNode(BinaryTreeNode node, BinaryTreeNode[] lastNode) {
        if (node!=null){
            if (node.left!=null){
                convertNode(node.left,lastNode);
            }
            node.left = lastNode[0];
            if (lastNode[0]!=null){
                lastNode[0].right = node;
            }
            lastNode[0] = node;
            if (node.right!=null){
                convertNode(node.right,lastNode);
            }
        }
    }
    public static void printList(BinaryTreeNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.right;
        }

        System.out.println("null");
    }

    public static void printTree(BinaryTreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.value + "->");
            printTree(root.right);
        }
    }
}
