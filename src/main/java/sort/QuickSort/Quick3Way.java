package sort.QuickSort;

import sort.Sort;

/**
 * 三向切分的快速排序
 * 直接在比较时交换元素
 */
public class Quick3Way extends Sort {
    public static void main(String[] args) {
        int[] arr = {2, 2, 5, 6, 2, 53, 3, 4, 2, 6, 1};
        quickSort(arr);
        show(arr);
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (high <= low) {
            return;
        }
        //arr[low]为目标元素
        //lt 使low-->lt-1的元素均小于目标元素
        //gt 使gt+1 --> high的元素均大于目标元素
        //i 使lt-->i 的元素均等于目标元素 实际比较元素的指针
        int lt = low, i = low + 1, gt = high;
        while (i <= gt) {
            if (arr[i] < arr[low]) {
                swap(arr, lt++, i++);//将较小元素放到lt前面，lt和i不断移动
            } else if (arr[i] > arr[low]) {
                swap(arr, i, gt--);//i没有+1是因为不知原先gt未知的元素是否大于目标元素
            } else {
                i++;
            }
        }
        quickSort(arr, low, lt - 1);
        quickSort(arr, gt + 1, high);
    }

}
