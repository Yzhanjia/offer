package designPattern.Singleton;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 14:37
 **/
public class StaticSingleton {
    public static int STATUS = 1;
    private StaticSingleton() {
        System.out.println("StaticSingleton is created");
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        System.out.println(StaticSingleton.STATUS);
    }
}
