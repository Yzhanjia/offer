package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 15:33
 **/
public class BadSuspend {
    public static Object object = new Object();
    static ChangedObjectThread thread1 = new ChangedObjectThread("t1");
    static ChangedObjectThread thread2 = new ChangedObjectThread("t2");

    private static class ChangedObjectThread extends Thread{

        public ChangedObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        thread1.start();
        Thread.sleep(100);
        thread2.start();
        thread1.resume();
        thread2.resume();
        thread1.join();
        thread2.join();
    }
}
