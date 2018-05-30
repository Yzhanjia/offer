package leetcode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
给定一个二叉树，返回其按层次遍历的节点值
例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
 */

public class levelOrder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        levelOrder(root);
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (root==null){
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int nextLevel = 0;//记录下一层节点数
        int toBeAdd = 1;//当前层节点数，从第一层开始
        List<Integer> list = new ArrayList();
        while (!queue.isEmpty()){
            TreeNode node = queue.peek();//队列第一元素，记录每一层的子节点
            list.add(node.val);
            if (node.left!=null){
                queue.offer(node.left);//子节点入列
                nextLevel++;//左子节点，下一层节点数+1
            }
            if (node.right!=null){
                queue.offer(node.right);
                nextLevel++;
            }
            queue.poll();
            toBeAdd--;//当前层节点数-1
            if (toBeAdd==0){//遍历完当前节点
                //若直接add(list),list清空时，会把lists中的list清空
                lists.add((List<Integer>) ((ArrayList<Integer>) list).clone());
                list.clear();
                toBeAdd = nextLevel;
                nextLevel = 0;
            }

        }
        return lists;
    }

}
