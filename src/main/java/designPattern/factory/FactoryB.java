package designPattern.factory;

public class FactoryB implements Factory {
    @Override
    public Product create() {
        return new ProductB();
    }
}
