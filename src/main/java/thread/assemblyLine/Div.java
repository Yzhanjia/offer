package thread.assemblyLine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-06-08 09:39
 **/
public class Div implements Runnable {
    public static BlockingQueue<Msg> msgBlockingQueue = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = msgBlockingQueue.take();
                msg.i = msg.i / 2;
                System.out.println(msg.orgStr + " = " + msg.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
