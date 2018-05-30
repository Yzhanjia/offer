package designPattern.strategy;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    public  void swim(){
        System.out.println("Every duck can swim");
    }

    public void fly(){
        flyBehavior.fly();
    }
    public void quack(){
        quackBehavior.quack();
    }
}
