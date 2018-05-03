package designPattern.strategy;

public class BlueDuck extends Duck {
    public BlueDuck() {
        flyBehavior = new FlyWithRock();
        quackBehavior = new BQuack();
    }
}
