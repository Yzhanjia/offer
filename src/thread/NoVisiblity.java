package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 16:06
 **/
public class NoVisiblity {
    private static  boolean ready;
    private static  int number;

    private static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!ready) ;
            System.out.println(number);

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }

}
