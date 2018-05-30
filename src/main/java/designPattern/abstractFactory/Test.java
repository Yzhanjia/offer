package designPattern.abstractFactory;

public class Test {
    public static void main(String[] args) {
        PcFactory dellFactory = new DellFactory();
        PcFactory asusFactory =new AsusFactory();
        dellFactory.createKeyBoard().Hi();
        asusFactory.createMouse().Hi();

    }
}
