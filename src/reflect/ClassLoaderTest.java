package reflect;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-25 14:47
 **/
public class ClassLoaderTest {



    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader: " + loader);
        System.out.println("parent: " + loader.getParent());
        System.out.println("grandparent loader:" + loader.getParent().getParent());

    }
}
