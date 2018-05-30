package thread;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-26 19:12
 **/
public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20,timestamp,timestamp+1)) {
                                    System.out.println("余额小于20元，充值成功，余额： " + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                // System.out.println("余额大于20元，无需充值。" + "余额为" + money.get() + "元");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }
        new Thread() {
            @Override
            public void run () {
                for (int j = 0; j < 100; j++) {
                    int timestamp = money.getStamp();
                    while (true) {
                        Integer m = money.getReference();
                        if (m > 10) {
                            System.out.println("余额为" + m + "元，大于10元");
                            if (money.compareAndSet(m, m - 10,timestamp,timestamp+1)) {
                                System.out.println("成功消费10元，余额： " + money.getReference() + "元。");
                                break;
                            }
                        } else {
                            System.out.println("没有足够金额。");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
}
