package thread.assemblyLine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-06-08 09:29
 **/
public class Plus implements Runnable {
    public static BlockingQueue<Msg> msgBlockingQueue = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = msgBlockingQueue.take();
                msg.j = msg.i + msg.j;
                Multiply.msgBlockingQueue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
