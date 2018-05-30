package thread.Disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 15:56
 **/
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer buffer) {
        long sequence = ringBuffer.next();
        try {
            PCData event = ringBuffer.get(sequence);
            event.setValue(buffer.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
