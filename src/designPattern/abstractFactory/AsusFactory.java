package designPattern.abstractFactory;

public class AsusFactory implements PcFactory {
    @Override
    public Mouse createMouse() {
        return new AsusMouse();
    }

    @Override
    public KeyBoard createKeyBoard() {
        return new AsusKeyBoard();
    }
}
