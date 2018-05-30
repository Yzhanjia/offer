package designPattern.strategy;

public class RQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("gagaga");
    }
}
