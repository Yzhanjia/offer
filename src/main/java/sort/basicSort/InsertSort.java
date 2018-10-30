package sort.basicSort;

import sort.Sort;

/**
 * 插入排序，将元素移到比它大的元素前边
 */
public class InsertSort extends Sort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 3, 5, 7, 3, 5};
//        insertSort1(arr);
        insertSort2(arr);
        for (int i : arr
        ) {
            System.out.println(i);
        }
    }

    /**
     * 使用交换的做法
     *
     * @param arr
     */
    public static void insertSort1(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            //将每个元素插到前面去
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    /**
     * 不使用交换，直接将元素向后移动一位
     *
     * @param arr
     */
    public static void insertSort2(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int temp = arr[i];//记录当前值
            int j;
            for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];//循环结束时，找到位置使元素前面的元素均小于该元素
            }
            arr[j + 1] = temp;
        }
    }

}
