package thread;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-27 16:28
 **/
public class ThreadGroupName implements Runnable {
    @Override
    public void run() {
        String groupName = Thread.currentThread().getThreadGroup().getName() +
                "-" + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("PrintGroup");
        Thread thread1 = new Thread(threadGroup, new ThreadGroupName(), "T1");
        Thread thread2 = new Thread(threadGroup, new ThreadGroupName(), "T2");
        thread1.start();
        thread2.start();
        System.out.println(threadGroup.activeCount());
        threadGroup.list();
    }
}
