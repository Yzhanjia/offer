package sort.basicSort;

import sort.Sort;

/**
 * 希尔排序，基于插入排序
 * 使任意间隔为h的元素都是有序的
 * 先找到适合的h，再不断缩小h
 */
public class ShellSort extends Sort {
    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 4, 6, 23, 6, 2, 4, 21, 3, 1};
        shellSort(arr);
        show(arr);
    }

    public static void shellSort(int[] arr) {
        int N = arr.length;
        int h = 1;
        while (h < N / 3) {
            h = h * 3 + 1;//1,4,13,40,121,364.....获取适合的h
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                //将arr[i]插入arr[i-h],a[i - 2h]....中
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    swap(arr, j, j - h);
                }
            }
            h = h / 3;
        }
    }

}
