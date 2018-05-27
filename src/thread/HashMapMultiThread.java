package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 18:15
 **/
public class HashMapMultiThread {
    static Map<String, String> map = new ConcurrentHashMap<>();
//    static Map<String, String> map = new HashMap<>();

    public static class AddThread implements Runnable {
        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i += 2) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new HashMapMultiThread.AddThread(0));
        Thread thread2 = new Thread(new HashMapMultiThread.AddThread(1));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(map.size());
    }
}
