package designPattern.Singleton;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-30 14:29
 **/
public class Singleton {
//    public static int STATUS = 1;

    private Singleton() {
        System.out.println("Singleton is created");
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
//        System.out.println(Singleton.STATUS);
    }
}
