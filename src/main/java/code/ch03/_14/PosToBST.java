package code.ch03._14;

import code.ch03.Node;

/**
 * 根据后序数组重建搜索二叉树
 * 1.给定一个整型数组，无重复值，判断其是否为搜索二叉树的后序遍历的结果
 * 2.已知是搜索二叉树的后序遍历结果，通过数组重构二叉树
 */
public class PosToBST {
    public static boolean isPostArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isPost(arr, 0, arr.length - 1);
    }

    private static boolean isPost(int[] arr, int start, int end) {
        if (start == end) {
            return true;//不断靠近，最终叶子节点的start==end
        }
        int less = -1;//左子树的最右节点
        int more = end;//右子树的最左节点
        for (int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {
                less = i;//不断更新临界点
            } else {
                more = more == end ? i : more;//找到第一个比根节点大的值就不再更新
            }
        }
        if (less == -1 || more == end) {//无左子树或右子树
            return isPost(arr, start, end - 1);//end - 1为子树的根节点
        }
        if (less != more - 1) {//左子树的最右节点应该在右子树的最左节点的左边
            return false;
        }
        //判断左子树和右子树是不是
        return isPost(arr, start, less) && isPost(arr, more, end - 1);
    }

    /**
     * 将后序数组转为搜索二叉树
     *
     * @param posArr
     * @return
     */
    public Node posArrayToBST(int[] posArr) {
        if (posArr == null) {
            return null;
        }
        return posToBST(posArr, 0, posArr.length - 1);
    }

    private Node posToBST(int[] posArr, int start, int end) {
        if (start > end) {
            return null;
        }
        Node head = new Node(posArr[end]);
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (posArr[end] > posArr[i]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }
        head.left = posToBST(posArr, start, less);
        head.right = posToBST(posArr, more, end - 1);
        return head;
    }
}
