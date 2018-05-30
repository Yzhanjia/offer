package designPattern.factory;

public class Test {
    public static void main(String[] args) {
        Factory factoryA = new FactoryA();
        Factory factoryB = new FactoryB();
        factoryA.create();
        factoryB.create();
    }
}
