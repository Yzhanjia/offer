package thread.Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-06-08 09:19
 **/
public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(new RealData_jdk("a"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //开启线程进行call()执行
        executorService.submit(future);

        System.out.println("请求完毕");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //若call()没有执行完成，则依然会等待
        System.out.println("数据 = " + future.get());
    }
}
