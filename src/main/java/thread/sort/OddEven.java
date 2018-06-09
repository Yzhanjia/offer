package thread.sort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-06-08 10:11
 **/

public class OddEven {
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /*
    奇偶交换串行实现
     */
    public static void oddEnenSort(int[] arr) {
        int exchFlag = 1, start = 0;
        while (exchFlag == 1 || start == 1) {
            exchFlag = 0;
            for (int i = start; i < arr.length - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    exchFlag = 1;
                }
            }
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
    }

    /*
      奇偶排序 并行实现
     */
    static int flag = 1;

    public synchronized static int getFlag() {
        return flag;
    }

    public synchronized static void setFlag(int v) {
        flag = v;
    }

//    public static class OddEvenSortTask implements Runnable {
//        int i;
//        CountDownLatch latch;
//
//
//        public OddEvenSortTask(int i, CountDownLatch latch) {
//            this.i = i;
//            this.latch = latch;
//        }
//
//        @Override
//        public void run() {
//            if (arr[i] > arr[i + 1]) {
//                int temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//                setFlag(1);
//            }
//            latch.countDown();
//        }
//    }
//
//    public static void pOddEvenSort(int[] arr) {
//        int start = 0;
//        ExecutorService service = Executors.newFixedThreadPool(2);
//        while (getFlag() == 1 || start == 1) {
//            setFlag(0);
//            CountDownLatch latch = new CountDownLatch(arr.length / 2 - (arr.length % 2 == 0 ? start : 0));
//
//            }
//        }
//    }
}
