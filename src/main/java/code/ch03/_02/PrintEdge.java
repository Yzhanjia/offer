package code.ch03._02;

import code.ch03.Node;

/**
 * 打印边界节点
 */

public class PrintEdge {
    /**
     * 规则：头结点，每一层最左最右，叶子节点为边界节点
     *
     * @param head
     */
    public static void printEdge1(Node head) {
        if (head == null) {
            return;
        }
        //获取树的高度
        int height = getHeight(head, 0);
        Node[][] edgeMap = new Node[height][2];
        //记录最左最右节点
        setEdgeMap(head, 0, edgeMap);
        //打印最左节点
        for (int i = 0; i != edgeMap.length; i++) {
            System.out.println(edgeMap[i][0].value + " ");
        }
        //打印非最左最右，但是是叶子节点的节点
        printLeafNotInMap(head, 0, edgeMap);
        //打印最右节点
        for (int i = edgeMap.length - 1; i != -1; i--) {
            System.out.println(edgeMap[i][1].value + " ");
        }
        System.out.println();
    }


    private static void printLeafNotInMap(Node head, int l, Node[][] edgeMap) {
        if (head == null) {
            return;
        }
        if (head.left == null && head.right == null && head != edgeMap[l][0] && head != edgeMap[l][1]) {
            System.out.println(head.value + " ");
        }
        //从左到右打印
        printLeafNotInMap(head.left, l + 1, edgeMap);
        printLeafNotInMap(head.right, l + 1, edgeMap);

    }

    private static void setEdgeMap(Node head, int l, Node[][] edgeMap) {
        if (head == null) {
            return;
        }
        edgeMap[l][0] = edgeMap[l][0] == null ? head : edgeMap[l][0];//主要针对最右节点，如果最左未确定，则右节点同时最左节点
        edgeMap[l][1] = head;//可覆盖之前的值
        setEdgeMap(head.left, l + 1, edgeMap);//先将最左最右节点均设为最左节点
        setEdgeMap(head.right, l + 1, edgeMap);//从左到右确定
    }

    private static int getHeight(Node head, int l) {
        if (head == null) {//到叶子节点停止计数
            return l;
        }
        return Math.max(getHeight(head.left, l + 1), getHeight(head.right, l + 1));//比较左右子树的高度
    }

    /**
     * 头结点，叶子节点为边界节点，边界节点的延伸为边界节点，即打印最外层的节点
     */
    public static void printEdge2(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        if (head.left == null && head.right == null) {
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
        System.out.println();
    }

    private static void printLeftEdge(Node head, boolean print) {
        if (head == null) {
            return;
        }
        //叶子节点和边界节点的延伸为边界节点
        if (print || (head.left == null && head.right == null)) {
            System.out.println(head.value + " ");
        }
        printLeftEdge(head.left, print);//左边界的左节点为边界节点
        printLeftEdge(head.right, print && head.left == null);//如果左边界的左节点为空，则左边界的右节点为边界节点
    }

    private static void printRightEdge(Node head, boolean print) {
        if (head == null) {
            return;
        }
        printRightEdge(head.left, print && head.right == null);
        printRightEdge(head.right, print);
        if (print || (head.left == null && head.right == null)) {
            System.out.println(head.value + " ");//递归到底层，从下往上打印
        }
    }
}
