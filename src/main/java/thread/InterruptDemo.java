package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 15:01
 **/
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("Interrupted");
                        break;
                    }
                    Thread.yield();
                }

            }
        };
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
