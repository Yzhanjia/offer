package code.ch02._08;

import code.ch02.Node;

/**
 * 将单向链表按某值分为左边小，中间相等，右边的的形式
 * 进阶 : 分组后的顺序与原来先后顺序相同
 */
public class Partition {
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[count];//将所有节点放入数组中便于排序
        cur = head;
        for (int i = 0; i != count; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPart(nodeArr, pivot);//类似快速排序分组
        for (int i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];//排序完直接重新连接起来
        }
        nodeArr[count - 1].next = null;//最后一个节点
        return nodeArr[0];
    }

    /**
     * 类似快速排序，但只排序一个
     *
     * @param nodeArr
     * @param pivot
     */
    private static void arrPart(Node[] nodeArr, int pivot) {
        int small = -1;//小指针不断向后移动
        int big = nodeArr.length;//大指针不断向前移动
        int index = 0;//当前节点
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);//将当前节点移到前面
            } else if (nodeArr[index].value == pivot) {
                index++;//不处理
            } else {
                swap(nodeArr, --big, index);//将较大节点移到后面
            }
        }
    }

    private static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    /**
     * 符合先后顺序的方法
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;//左部分的头结点
        Node sT = null;//左部分的尾节点
        Node eH = null;//中间部分
        Node eT = null;
        Node bH = null;//右部分
        Node bT = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;//增加在左部分的尾部
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        if (sT != null) {//有左部分
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {//有右部分
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
    }
}
