package thread.Future;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 20:23
 **/
public class Client {


    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);//RealData的构造很慢，所以在单独线程中执行
                future.setRealData(realData);
            }
        }.start();
        return future;//FutureData会被立即返回
    }
}
