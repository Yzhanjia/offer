package thread.Disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 15:55
 **/
public class PCDataFactory implements EventFactory<PCData> {
    public PCData newInstance() {
        return new PCData();
    }
}
