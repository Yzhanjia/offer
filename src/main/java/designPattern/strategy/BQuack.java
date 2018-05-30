package designPattern.strategy;

public class BQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("hahaha");
    }
}
