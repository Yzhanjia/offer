package question;

public class ExtendTest {
    public static class A {
//        public A(int v) {
//            this.setValue();
//            System.out.println(v);
//        }

        public void setValue() {
            System.out.println("A.set");
        }

        public void try1() {
            setValue();
        }
    }

    public static class B extends A {

//        public B() {
//            super(5);
//        }

        @Override
        public void setValue() {
            System.out.println("B.setValue");
        }

        public void test() {
            super.try1();
        }
    }

    public static void main(String[] args) {
        new B().test();
    }
}
