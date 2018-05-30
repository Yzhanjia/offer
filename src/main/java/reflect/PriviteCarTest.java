package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-25 15:12
 **/
public class PriviteCarTest {
    public static void main(String[] args) throws Throwable {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("reflect.PrivateCar");
        PrivateCar privateCar = (PrivateCar) clazz.newInstance();
        Field field = clazz.getDeclaredField("color");

        //取消Java访问检查以访问private变量
        field.setAccessible(true);
        field.set(privateCar,"红色");

        Method method = clazz.getDeclaredMethod("drive",(Class[])null);

        method.setAccessible(true);
        method.invoke(privateCar,null);

    }
}
