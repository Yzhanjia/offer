package sort.QuickSort;

import sort.Sort;

public class BasicQuick extends Sort {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 9, 2, 1, 6};
        quickSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序的核心
     * 切分元素前，切分元素，切分元素后
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        //获取切分元素，调用后j位置的元素前半部分全小于该元素，后半部分全大于该元素
        int j = partition(arr, low, high);
        //前半部分递归快排
        quickSort(arr, low, j - 1);
        //后半部分递归快排
        quickSort(arr, j + 1, high);
    }

    /**
     * 获取切分元素
     *
     * @param arr  目标数组
     * @param low  分组的头
     * @param high 分组的的尾
     * @return
     */
    private static int partition(int[] arr, int low, int high) {
        int i = low, j = high + 1;//i一开始要+1，即目标元素后一个元素，j要-1，即最后一个元素
        int v = arr[low];//切分元素，可随机挑选一个
        while (true) {
            //目的使左边均小于目标元素
            //右边均大于目标元素
            while (arr[++i] <= v) {//直到找到比目标元素大的元素，否则直接移到下一位置
                if (i == high) break;
            }
            while (v <= arr[--j]) {//直到找到比目标元素小的元素，否则移入下一位置
                if (j == low) break;
            }
            if (i >= j) {//两个指针相遇时，退出循环，即交换结束
                break;
            }
            swap(arr, i, j);//找到两个不正确的元素进行交换
        }
        swap(arr, low, j);//将arr[j]放入正确的切分位置
        return j;
    }

}
