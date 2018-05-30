package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 18:21
 **/
public final class FalseSharing implements Runnable {
    public final static int NUM_THRTADS = 2;//change
    public final static long ITERATIONS = 500l * 1000l * 1000l;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THRTADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THRTADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        for (Thread t : threads
                ) {
            t.start();
        }
        for (Thread t : threads
                ) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration = " + (System.currentTimeMillis() - start));
    }

    private static class VolatileLong {
        public volatile long value = 0L;
//        public long p1,p2,p3,p4,p5,p6,p7;
    }
}
