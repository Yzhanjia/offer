package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-26 09:11
 **/
public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger();
//    static Integer k  = new Integer(0);
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i.incrementAndGet();
//                   k ++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new  Thread[10];
        for (int j = 0; j < 10; j++) {
//            threads[j] = new Thread(new AddThread());
//            threads[j] = new Thread();
            threads[j] = new AddThread();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }
        System.out.println(i);
    }
}
