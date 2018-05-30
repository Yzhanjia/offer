package designPattern.ProducerAndConsumer;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 14:56
 **/
public final class PCData {
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data:" + intData;
    }
}
