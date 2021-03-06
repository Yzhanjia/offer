package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-26 19:47
 **/
public class AtmoicIntegerFieldUpdaterDemo {
    public static class Candidate{
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater
            = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate candidate = new Candidate();
        Thread[] threads = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(candidate);
                        allScore.incrementAndGet();
                    }
                }
            };
            threads[i].start();
        }
        for (int i = 0; i < 10000; i++) {
            threads[i].join();
            System.out.println("score = " + candidate.score);
            System.out.println("allScore = " + allScore);
        }
    }
}
