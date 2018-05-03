package designPattern.strategy;

public class FlyWithRock implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Rock");
    }
}
