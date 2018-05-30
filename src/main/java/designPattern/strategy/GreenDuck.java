package designPattern.strategy;

public class GreenDuck extends Duck{
    public GreenDuck() {
        flyBehavior = new FlyNoWings();
        quackBehavior = new GQuack();
    }
}
