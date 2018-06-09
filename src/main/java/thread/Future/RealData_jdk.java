package thread.Future;

import java.util.concurrent.Callable;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-06-08 09:14
 **/
public class RealData_jdk implements Callable<String> {
    private String para;

    public RealData_jdk(String para) {
        this.para = para;
    }

    @Override
    public String call() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
