package code.ch03._11;

import code.ch03.Node;

/**
 * 判断t1是否包含t2的全部拓扑结构
 */
public class ContainsAnotherTree {
    public static boolean contains(Node t1, Node t2) {
        return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    //先序遍历判断是否相同
    private static boolean check(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }
        if (t1.value != t2.value) {
            return false;
        }
        return check(t1.left, t2.left) && check(t1.right, t2.right);
    }
}
