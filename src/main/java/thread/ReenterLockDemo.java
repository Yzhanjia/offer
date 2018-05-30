package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-29 23:18
 **/
public class ReenterLockDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
//            lock.lock();//释放锁少，相当于未释放
            try {
                i++;
            } finally {
                lock.unlock();
//                lock.unlock();//释放过多，IllegalMonitorStateException
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockDemo reenterLockDemo = new ReenterLockDemo();
        Thread thread1 = new Thread(reenterLockDemo);
        Thread thread2 = new Thread(reenterLockDemo);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
