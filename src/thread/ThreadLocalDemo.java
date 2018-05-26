package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {
    static volatile ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString() + "is gc");
        }
    };
    static volatile CountDownLatch count = new CountDownLatch(10000);
    public static class ParseDate implements Runnable{
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            if (threadLocal.get() == null){
                threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println(this.toString() + "is gc");
                    }
                });
                System.out.println(Thread.currentThread().getId() + ":create SimpleDateFormat");
            }
            try {
                Date date = threadLocal.get().parse("2018-05-19 19:19:" + i%60);
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                count.countDown();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new ParseDate(i));
        }
        count.await();
        System.out.println("mission complete!!");
        threadLocal = null;
        System.gc();
        System.out.println("first GC complete");
        threadLocal = new ThreadLocal<SimpleDateFormat>();
        count = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new ParseDate(i));
        }
        count.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("second GC complete!!");
    }
}
