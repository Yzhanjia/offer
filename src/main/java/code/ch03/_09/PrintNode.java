package code.ch03._09;

import code.ch03.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1.实现二叉树的层次遍历并打印层数换行
 * 2.实现二叉树的ZigZag打印
 */
public class PrintNode {
    /**
     * 层次遍历
     *
     * @param head
     */
    public static void printByLevel(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        int level = 0;
        Node last = head;//记录每一层的最右节点
        Node nLast = null;//不断记录遍历时下一行的最右节点
        queue.offer(head);
        System.out.println("Level " + (level++) + " : ");
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.println(head.value + " ");
            if (head.left != null) {
                queue.offer(head.left);
                nLast = head.left;
            }
            if (head.right != null) {
                queue.offer(head.right);
                nLast = head.right;
            }
            if (head == null && !queue.isEmpty()) {
                System.out.println("\nLevel" + (level++) + " : ");
                last = nLast;
            }
        }
    }

    /**
     * 按ZigZag打印
     *
     * @param head
     */
    public static void printByZigZag(Node head) {
        if (head == null) {
            return;
        }
        Deque<Node> deque = new LinkedList<>();//双端队列
        int level = 1;
        boolean lToR = true;//true表示从左到右打印，false表示从右到左打印
        Node last = head;
        Node nLast = null;
        deque.offerFirst(head);
        printLevelAndOrientation(level++, lToR);//打印行号和方向
        while (!deque.isEmpty()) {
            if (lToR) {//从左往右打印
                head = deque.pollFirst();//弹出队列头
                if (head.left != null) {
                    nLast = nLast == null ? head.left : nLast;//新的一层的最后一个节点
                    deque.offerLast(head.left);//从队尾进入
                }
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    deque.offerLast(head.right);//依次从队尾进入，即从队尾弹出时，从右往左打印
                }
            } else {//从右往左打印
                head = deque.pollLast();//弹出队尾的节点
                if (head.right != null) {
                    nLast = nLast == null ? head.right : nLast;
                    deque.offerFirst(head.right);//从队头进入
                }
                if (head.left != null) {
                    nLast = nLast == null ? head.right : nLast;
                    deque.offerFirst(head.right);//从队头进入，弹出时从左往右打印
                }
            }
            System.out.println(head.value + " ");//子节点都进队列，开始打印该节点
            if (head == last && !deque.isEmpty()) {//当达到当前层的最后一个节点时调换方向
                lToR = !lToR;//调换方向
                last = nLast;
                //从下一层开始时，先找出原先下一层的最后节点，再讲下一层的最后节点设为null,下一层要逆转反向
                nLast = null;
                System.out.println();
                printLevelAndOrientation(level++, lToR);//进入下一层
            }
        }
    }

    private static void printLevelAndOrientation(int level, boolean lToR) {
        System.out.print("Level " + level + " from ");
        System.out.print(lToR ? "left to right: " : "right to left: ");
    }
}
