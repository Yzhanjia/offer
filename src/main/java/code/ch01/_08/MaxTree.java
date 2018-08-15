package code.ch01._08;

import java.util.HashMap;
import java.util.Stack;

/**
 * 数组无重复元素
 * MaxTree为二叉树，数组每一个值对应一个二叉树节点
 * 每棵树的最大节点是该树的头结点
 */
public class MaxTree {
    /**
     * 原则：每一个数的父节点是它左边第一个比它大的数和它右边第一个比它大的数中较小的一个
     * 左边无比它大的数，右边无比它大的数，该数为数组的最大值，即树的头结点
     *
     * @param arr
     * @return
     */
    public Node getMaxTree(int[] arr) {
        Node[] nArr = new Node[arr.length];
        for (int i = 0; i != arr.length; i++) {
            nArr[i] = new Node(arr[i]);//根据数组中的值生成对应的节点
        }

        Stack<Node> stack = new Stack<>();//栈中保持递减序列，栈底最大，找到左右第一个比当前数
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();
        //找到每个节点左边第一个比当前节点大的节点
        for (int i = 0; i != nArr.length; i++) {
            Node curNode = nArr[i];
            //只有当栈顶元素小于当前节点时，弹出栈并记录当前节点的对应第一个较大值
            while (!stack.isEmpty() && stack.peek().value < curNode.value) {
                popStackSetMap(stack, lBigMap);
            }
            //将当前节点压入栈中
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {//栈不为空，可以直接弹出并记录对应值，此时栈已全部从小到大排序
            popStackSetMap(stack, lBigMap);
        }
        //找到右边第一个比当前节点大的节点
        for (int i = nArr.length - 1; i != -1; i--) {
            Node curNode = nArr[i];
            while (!stack.isEmpty() && stack.peek().value < curNode.value) {
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, rBigMap);
        }

        Node head = null;

        for (int i = 0; i != nArr.length; i++) {
            Node curNode = nArr[i];
            Node left = lBigMap.get(curNode);//左边第一个比当前节点大的节点
            Node right = rBigMap.get(curNode);//右边第一个比当前节点大的节点
            if (left == null && right == null) {
                head = curNode;
            } else if (left == null) {//左边没有比当前节点大的节点
                if (right.left == null) {
                    right.left = curNode;
                } else {
                    right.right = curNode;
                }
            } else if (right == null) {
                if (left.left == null) {
                    left.left = curNode;
                } else {
                    left.right = curNode;
                }
            } else {
                //比较两个节点，父节点为较小的节点
                Node parent = left.value < right.value ? left : right;
                if (parent.left == null) {
                    parent.left = curNode;
                } else {
                    parent.right = curNode;
                }
            }
        }
        return head;
    }

    private void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map) {
        Node popNode = stack.pop();
        if (stack.isEmpty()) {
            map.put(popNode, null);
        } else {
            map.put(popNode, stack.peek());//第一个比当前节点大的节点为上一个节点
        }

    }

}
