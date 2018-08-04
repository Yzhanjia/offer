package code.ch03._03;

import code.ch03.Node;

/**
 * 直观打印树
 */
public class PrintTreeNode {
    public static void printTree(Node head) {
        System.out.println("Binary Tree: ");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    /*
       中序打印
     */
    private static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);//"v"表示父节点在上一列，上一行
        //实现对称，格式一定，int最大长度为17，不足左右补齐
        String value = to + head.value + to;
        int lenM = value.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        value = getSpace(lenL) + value + getSpace(lenR);
        System.out.println(getSpace(height * len) + value);//分层打印
        printInOrder(head.left, height + 1, "^", len);
    }

    /*
    打印空格
     */
    private static String getSpace(int num) {
        String space = " ";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            buffer.append(space);
        }
        return buffer.toString();
    }
}
