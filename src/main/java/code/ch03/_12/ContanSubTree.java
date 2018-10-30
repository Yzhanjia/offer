package code.ch03._12;

import code.ch03.Node;

import static code.ch03._04.SerialTreeNode.serialByPre;
import static code.ch09._31.KMP.getIndexOf;

/**
 * 判断t1是否有t2中完全一样的子树结构
 */
public class ContanSubTree {
    public boolean isSubtree(Node t1, Node t2) {
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);//序列化两颗二叉树
        return getIndexOf(t1Str, t2Str) == -1;//KMP比较t1Str是否包含t2Str全部字符
    }
}
