package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 16:35
 **/
public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new DaemonT();
//        thread.setDaemon(true);
        thread.start();
//        thread.setDaemon(true);

        Thread.sleep(2000);
    }
}
