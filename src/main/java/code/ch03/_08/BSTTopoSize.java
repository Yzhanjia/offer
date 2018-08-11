package code.ch03._08;

import code.ch03.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 找到二叉树中的最大搜索二叉子树
 * 给定头结点，找到含有节点最多的二叉搜索树的拓扑结构
 */
public class BSTTopoSize {
    /**
     * 一棵树的左右子树都是二叉搜索树，且该树的头结点小于右子节点，大于左子节点，则这棵树是二叉搜索树
     * 利用二叉搜索的方式移动求解
     *
     * @param head
     * @return
     */
    public static int bstTopoSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int max = maxTopo(head, head);
        //找出最大的二叉搜索树的节点数
        max = Math.max(bstTopoSize1(head.left), max);//与左子树的中的最大二叉搜索树的节点数比较
        max = Math.max(bstTopoSize1(head.right), max);//与右子树比较
        return max;
    }

    //找出最大二叉搜索树的节点数
    private static int maxTopo(Node head, Node node) {
        if (head != null && node != null && isBSTNode(head, node, node.value)) {
            //左子树的符合二叉搜索树的节点数的和右子树的符合二叉树的节点数加上头结点
            // 从头结点开延伸成一棵二叉搜索树
            return maxTopo(head, node.left) + maxTopo(head, node.right) + 1;
        }
        return 0;
    }

    private static boolean isBSTNode(Node head, Node node, int value) {
        //找不到该节点证明该节点加入拓扑结构不符合二叉搜索树的条件
        if (head == null) {
            return false;
        }
        //通过大小比较找到该节点
        if (head == node) {
            return true;
        }
        //通过二叉树的性质，比较大小，不断递归查找，看能否找到该节点
        return isBSTNode(head.value > value ? head.left : head.right, node, value);
    }

    /**
     * 利用拓扑贡献记录，将子树的贡献相加，减去不符合要求的部分
     */
    public static class Record {
        public int l;
        public int r;

        public Record(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static int bstTopoSize2(Node head) {
        Map<Node, Record> map = new HashMap<>();//key为节点，value为对应的左右贡献值
        //后序遍历
        return posOrder(head, map);
    }


    private static int posOrder(Node head, Map<Node, Record> map) {
        if (head == null) {
            return 0;
        }
        //分别后序遍历左右子树
        int ls = posOrder(head.left, map);
        int rs = posOrder(head.right, map);
        //对源节点的实际操作
        modifyMap(head.left, head.value, map, true);
        modifyMap(head.right, head.value, map, false);
        Record lr = map.get(head.left);//左子树的贡献
        Record rr = map.get(head.right);//右子树的贡献
        //子树加上本省总的贡献值
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        //记录新的贡献值
        map.put(head, new Record(lbst, rbst));
        //左右子树的贡献总和和左右子树的子树的贡献总和进行比较
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    /**
     * @param node  要操作的子节点
     * @param value 节点的值
     * @param map   记录Node与贡献值的映射
     * @param s     true为左子节点，false为右子节点
     * @return
     */
    private static int modifyMap(Node node, int value, Map<Node, Record> map, boolean s) {
        if (node == null || (!map.containsKey(node))) {
            return 0;
        }
        Record record = map.get(node);
        //左子节点的值大于头结点或者右子节点的值小于头结点
        //即不符合二叉搜索树的性质，将该子节点移去
        if ((s && node.value > value) || (!s) && node.value < value) {
            map.remove(node);
            return record.l + record.r + 1;//返回要删除的贡献值
        } else {
            //左子节点满足，则递归遍历查找左子树的右子树部分是否不符合
            //右子节点满足，则递归遍历查找右子树的左子树部分是否不符合
            int minus = modifyMap(s ? node.right : node.left, value, map, s);
            if (s) {
                record.r = record.r - minus;//减去删除子树的右贡献值
            } else {
                record.l = record.l - minus;//减去删除子树的左贡献值
            }
            map.put(node, record);//将新的贡献值存入map，记录对应映射
            return minus;

        }
    }

}
