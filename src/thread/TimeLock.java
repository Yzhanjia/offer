package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 00:05
 **/
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("执行！");
                Thread.sleep(6000);
                System.out.println("休息结束！");
            } else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread thread1 = new Thread(timeLock);
        Thread thread2 = new Thread(timeLock);
        thread1.start();
        thread2.start();
    }
}
