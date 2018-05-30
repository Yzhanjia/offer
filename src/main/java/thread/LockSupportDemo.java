package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 09:26
 **/
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangedObjectThread t1 = new ChangedObjectThread("t1");
    static ChangedObjectThread t2 = new ChangedObjectThread("t2");

    private static class ChangedObjectThread extends Thread{
        public ChangedObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
