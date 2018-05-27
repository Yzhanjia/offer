package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 17:31
 **/
public class PriortyDemo {
    public static class HightPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriortyDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("HighPriority is completed");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriortyDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("LowPriority is completed");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread high = new HightPriority();
        LowPriority low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}
