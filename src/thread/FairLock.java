package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 08:56
 **/
public class FairLock implements Runnable {
//    public static ReentrantLock fairlock = new ReentrantLock(true);
    public static ReentrantLock fairlock = new ReentrantLock();
    @Override
    public void run() {
        while (true) {
            try {
                fairlock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            } finally {
                fairlock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1, "Thread_1");
        Thread t2 = new Thread(r1, "Thread_2");
        t1.start();
        t2.start();
    }
}
