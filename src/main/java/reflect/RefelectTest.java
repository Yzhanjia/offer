package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @program: offer
 * @description:
 * @author: Mr.Yao
 * @create: 2018-05-25 14:17
 **/
public class RefelectTest {
    public static Car initByDefaultConst() throws Throwable {
        //通过类加载器获取Car对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("reflect.Car");
        //获取类的默认构造器对象并通过它实例化Car
        Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
        Car car = (Car) cons.newInstance();
        //通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "红旗CA72");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 200);
        return car;
    }

    public static void main(String[] args) {
//        Car car = initByDefaultConst();
        Car car = new Car("红旗CA72", "红色", 100);
        car.introduce();
    }
}
