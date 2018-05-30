package designPattern.Singleton;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 14:33
 **/
public class LazySingleton {
    public static int STATUS = 1;

    private LazySingleton() {
        System.out.println("LazySingleton is created");
    }
    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(LazySingleton.STATUS);
    }
}
