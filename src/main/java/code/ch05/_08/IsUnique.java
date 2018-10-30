package code.ch05._08;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断字符数组中是否所有的字符都只出现一次
 */
public class IsUnique {
    public static void main(String[] args) {
        char[] chars = {'a', 'f', 'g'};
//        char[] chars = {'a', 's', 'd', 'a', 's'};
//        System.out.println(isUnique1(chars));
        System.out.println(isUnique2(chars));
    }

    /**
     * 时间复杂度为O(N)
     *
     * @param chars
     * @return
     */
    public static boolean isUnique1(char[] chars) {
        if (chars == null) {
            return true;

        }
        //数组保存
//        boolean[] map = new boolean[256];
//        for (int i = 0; i < chars.length; i++) {
//            if (map[chars[i]]) {
//                return false;
//            }
//            map[chars[i]] = true;
//        }
        // 哈希表记录
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                return false;
            }
            set.add(chars[i]);
        }
        return true;
    }

    /**
     * 保证空间复杂度为O（1），通过排序完成
     *
     * @param chars
     * @return
     */
    public static boolean isUnique2(char[] chars) {
        if (chars == null) {
            return true;
        }
        heapSort(chars);//堆排序的非递归算法能满足空间复杂度的要求
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static void heapSort(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            heapInsert(chars, i);
        }
        for (int i = chars.length - 1; i > 0; i--) {
            //将最后一节点，放在顶部便于下沉
            swap(chars, 0, i);
            //下沉排序
            heapify(chars, 0, i);
        }
    }

    /**
     * @param chars
     * @param i
     * @param size  子堆的大小，均以头结点为顶部
     */
    private static void heapify(char[] chars, int i, int size) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while (left < size) {
            if (chars[left] > chars[i]) {
                largest = left;//先看左子节点是否大于父节点，若大于父节点，则直接交换
            }
            if (right < size && chars[right] > chars[largest]) {
                largest = right;//新的父节点是左子节点和右子节点中较大的一个
            }
            if (largest != i) {//已移动过
                swap(chars, largest, i);
            } else {
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    private static void swap(char[] chars, int index1, int index2) {
        char temp = chars[index1];
        chars[index1] = chars[index2];
        chars[index2] = temp;
    }

    /**
     * 构造堆，使堆有序化，父节点均大于子节点
     *
     * @param chars
     * @param i
     */
    private static void heapInsert(char[] chars, int i) {
        int parent = 0;
        while (i != 0) {
            parent = (i - 1) / 2;//不断上浮，直到父节点都大于子节点
            if (chars[parent] < chars[i]) {
                swap(chars, parent, i);
                i = parent;
            } else {
                break;
            }
        }
    }
}
