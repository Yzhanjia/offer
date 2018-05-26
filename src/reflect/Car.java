package reflect;

/**
 * @program: offer
 * @description: entity
 * @author: Mr.Yao
 * @create: 2018-05-25 14:10
 **/
public class Car {
    private String brand;
    private String color;
    private int maxSpeed;
//默认构造函数
    public Car() {
    }
//带参构造函数
    public Car(String brand, String color, int maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void introduce(){
        System.out.println("brand: "+ brand + "; color: " + color + "; maxSpeed: " + maxSpeed);
    }
}
