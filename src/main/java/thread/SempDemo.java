package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 09:16
 **/
public class SempDemo implements Runnable {
    final Semaphore semaphore = new Semaphore(5);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ": done!");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SempDemo demo = new SempDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(demo);
        }
    }
}
