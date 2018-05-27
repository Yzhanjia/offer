package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 18:28
 **/
public class BadLockOnInteger implements Runnable{
    public static Integer i = 0;
    static BadLockOnInteger instance = new BadLockOnInteger();

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
//            synchronized (i) {
//                i++;
//            }
            synchronized (instance) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
