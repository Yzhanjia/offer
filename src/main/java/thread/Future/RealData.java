package thread.Future;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 20:19
 **/
public class RealData implements Data {
    protected final String result;

    public RealData(String para) {//RealData的构造可能会很慢，需要用户等待很久，这里使用sleep模拟
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(para);
            try {//sleep代表一个很慢的操作过程
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = stringBuffer.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
