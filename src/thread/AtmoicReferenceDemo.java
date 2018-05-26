package thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-26 10:25
 **/
public class AtmoicReferenceDemo {
    static AtomicReference<Integer> money = new AtomicReference<>();

    public static void main(String[] args) {
        money.set(19);
        for (int i = 0; i < 3; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.get();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20)) {
                                    System.out.println("余额小于20元，充值成功，余额： " + money.get() + "元");
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
                    while (true) {
                        Integer m = money.get();
                        if (m > 10) {
                            System.out.println("余额为" + m + "元，大于10元");
                            if (money.compareAndSet(m, m - 10)) {
                                System.out.println("成功消费10元，余额： " + money.get() + "元。");
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


