package code.ch03._04;

import code.ch03.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化和反序列化二叉树
 */
public class SerialTreeNode {
    /**
     * 先序遍历
     */
    /**
     * 先序遍历序列化
     *
     * @param head
     * @return
     */
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    /**
     * 反序列化
     *
     * @param preString
     * @return
     */
    public static Node reconByPreString(String preString) {
        String[] value = preString.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < value.length; i++) {
            queue.offer(value[i]);
        }
        return reconPreOrder(queue);
    }

    private static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    /**
     * 层次遍历序列化和反序列化
     */
    /**
     * 序列化
     *
     * @param head
     * @return
     */
    public static String serialByLevel(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res += head.left.value + "!";
            } else {
                res += "#!";
            }
            if (head.right != null) {
                res += head.right.value + "!";
            } else {
                res += "#!";
            }
        }
        return res;
    }

    /**
     * 反序列化
     *
     * @param levelStr
     * @return
     */
    public static Node reconByLevelString(String levelStr) {
        String[] values = levelStr.split("!");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    private static Node generateNodeByString(String value) {
        if (value.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(value));
    }
}
