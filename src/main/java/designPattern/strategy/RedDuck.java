package designPattern.strategy;

public class RedDuck extends Duck {
    public RedDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new RQuack();
    }
}
