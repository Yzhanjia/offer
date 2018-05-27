package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 17:41
 **/
public class AccountVol implements Runnable {
    static AccountVol instance = new AccountVol();
    static volatile int i = 0;

    //    public static void increase() {
////        i++;
//        synchronized (instance) {
//            i++;
//        }
//    }
//    public synchronized void increase() {
//        i++;
//    }
    public static synchronized void increase() {
        i++;
    }
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(instance);
//        Thread thread2 = new Thread(instance);
        Thread thread1 = new Thread(new AccountVol());
        Thread thread2 = new Thread(new AccountVol());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
