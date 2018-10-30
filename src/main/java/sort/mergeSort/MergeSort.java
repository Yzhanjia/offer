package sort.mergeSort;

import sort.Sort;

/**
 * 归并排序，将两个有序数组归并为一个更大的有序数组
 */
public class MergeSort extends Sort {
    public static void main(String[] args) {
        int[] arr = {3, 32, 2, 4, 1, 5, 2, 7, 8, 4, 2};
//        mergeSort(arr);
        mergeSortBU(arr);
        show(arr);
    }

    private static int[] aux;

    /**
     * 自顶向下的归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        aux = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        //先分成两部分分别排序
        //递归调用，最底层为一组个数为2
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        //将两部分归并为一部分
        if (arr[mid] > arr[mid + 1]) {//判断使运行时间变为线性，如果为真，则子数组仍未有序-+
            merge(arr, low, mid, high);
        }
    }

    /**
     * 自底向上的归并排序
     *
     * @param arr
     */
    public static void mergeSortBU(int[] arr) {
        int N = arr.length;
        aux = new int[N];
        for (int sz = 1; sz < N; sz = sz + sz) {//sz为子数组的大小
            // 会多次遍历整个数组
            for (int low = 0; low < N - sz; low += sz + sz) {//low为子数组的索引
                //最后一个子数组只有在数组大小是sz的整数倍时最后一元素才是low + 2sz -1
                //否则最后子数组的最后一元素是数组的最后一元素
                merge(arr, low, low + sz - 1, Math.min(low + sz + sz - 1, N - 1));
            }
        }
    }

    /**
     * 原地归并
     *
     * @param arr
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(int[] arr, int low, int mid, int high) {
        int i = low, j = mid + 1;
        //复制数组
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k];
        }
        //核心步骤
        for (int k = low; k <= high; k++) {
            if (i > mid) {//左半边取尽
                arr[k] = aux[j++];//剩下全部为右半部分
            } else if (j > high) {//右半边取完
                arr[k] = aux[i++];//剩下全部分左半部分
            } else if (aux[j] < aux[i]) {
                arr[k] = aux[j++];//右半边的当前元素小于左半边的当前元素
            } else {
                arr[k] = aux[i++];//左半边的当前元素小于右半边的当前元素
            }

        }
    }
}
