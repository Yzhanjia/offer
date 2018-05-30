package thread.Disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 15:52
 **/
public class Consumer implements WorkHandler<PCData> {
    public void onEvent(PCData pcData) {
        System.out.println(Thread.currentThread().getId() + ": Event: --"
                + pcData.getValue() * pcData.getValue() + "--");
    }
}
