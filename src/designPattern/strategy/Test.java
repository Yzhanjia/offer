package designPattern.strategy;

public class Test {
    public static void main(String[] args) {
        Duck duck1 = new RedDuck();
        Duck duck2 = new GreenDuck();
        duck1.fly();
        duck1.quack();
        duck1.swim();
        duck2.fly();
        duck2.quack();
        duck2.swim();
        Duck duck3 = new BlueDuck();
        duck3.fly();
        duck3.quack();
    }
}
