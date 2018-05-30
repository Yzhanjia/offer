package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 15:42
 **/
public class GoodSuspend {
    public static Object object = new Object();

    public static class ChangeObjectThread extends Thread {
        volatile boolean suspendMe = false;

        public void suspendMe() {
            suspendMe = true;
        }

        public void resumeMe() {
            suspendMe = false;
            synchronized (this) {
                notify();
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspendMe) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    synchronized (object) {
                        System.out.println("in ChangeObjectThread");
                    }
                    Thread.yield();
                }
            }
        }

    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread thread1 = new ChangeObjectThread();
        ReadObjectThread thread2 = new ReadObjectThread();
        thread1.start();
        thread2.start();
        Thread.sleep(10000);
        thread1.suspendMe();
        System.out.println("suspend t1 20s");
        Thread.sleep(20000);
        System.out.println("resume t1");
        thread1.resumeMe();
    }

}
