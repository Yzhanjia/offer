package sort.heapSort;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        Comparable[] chars = {'a', 's', '8', 'e', '6', 'd', '9', '1'};
        heapSort(chars);
        for (Comparable i : chars
        ) {
            System.out.println(i);
        }
    }

    public static void heapSort(Comparable[] a) {
        int N = a.length;
        int n = a.length - 1;
        for (int i = N / 2; i >= 0; i--) {
            sink(a, i, n);//构造堆
        }
        while (n > 0) {
            swap(a, 0, n--);//将最后一个元素置于堆顶再下沉
            sink(a, 0, n);
        }
    }

    /**
     * 下沉算法，即使父节点均大于子节点
     *
     * @param a
     * @param i
     * @param n
     */
    private static void sink(Comparable[] a, int i, int n) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, i, j)) {
                break;
            }
            swap(a, i, j);
            i = j;
        }
    }

    private static void swap(Comparable[] a, int i, int size) {
        Comparable temp = a[i];
        a[i] = a[size];
        a[size] = temp;
    }

    private static boolean less(Comparable[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }
}
