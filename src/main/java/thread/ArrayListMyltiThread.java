package thread;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 18:07
 **/
public class ArrayListMyltiThread {
    //    static ArrayList<Integer> list = new ArrayList<>(10);
    static Vector<Integer> list = new Vector<>(10);
    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new AddThread());
        Thread thread2 = new Thread(new AddThread());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(list.size());
    }
}
