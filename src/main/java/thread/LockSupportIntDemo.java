package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 09:38
 **/
public class LockSupportIntDemo {
    public static Object object = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    private static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.interrupted())
                    System.out.println(getName() + "被中断了");
            }
            System.out.println(getName() + "执行结束！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t1);
    }
}
