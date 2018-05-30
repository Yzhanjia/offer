package thread.Future;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 20:18
 **/
public class FutureData implements Data {//Future是RealData的包装
    protected RealData realData = null;
    protected boolean isReady = false;

    protected synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();//RealData已经被注入，通知getResul()
    }

    @Override
    public synchronized String getResult() {//会等待Realdata构造完成
        while (!isReady) {
            try {
                wait();//一直等待，直到RealData被注入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;//有RealData实现
    }
}
