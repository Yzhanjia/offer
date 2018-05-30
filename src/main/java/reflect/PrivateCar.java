package reflect;

/**
 * @program: offer
 * @author: Mr.Yao
 * @create: 2018-05-25 15:09
 **/
public class PrivateCar {
    //私有成员变量，只能本类访问
    private String color;
    //protected方法，子类和本包访问
    protected void drive(){
        System.out.println("drive private car! the color is " + color);
    }
}
