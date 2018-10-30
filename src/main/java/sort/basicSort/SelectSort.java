package sort.basicSort;

import sort.Sort;

/**
 * 选择排序，每次找到最小值排到最前面
 */
public class SelectSort extends Sort {
    public static void selectSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[min]) {
                    min = j;//更新最小值
                }
            }
            swap(arr, i, min);
        }
    }
}
