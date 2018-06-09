package thread.assemblyLine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-06-08 09:35
 **/
public class Multiply implements Runnable {
    public static BlockingQueue<Msg> msgBlockingQueue = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = msgBlockingQueue.take();
                msg.i = msg.i * msg.j;
                Div.msgBlockingQueue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
