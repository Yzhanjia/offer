package question;

public class Combine {

    // n   ： 待分解的数
    // buf ： 保存当前已经分解的因子
    // top ： 指向当前已经分解好的最后一个因子 buf[top]
    public static void divide(int n, int[] buf, int top) {

        if (n == 0) {
            for (int i = 0; i <= top; i++) System.out.printf("%d ", buf[i]);
            System.out.println();
            return;
        }

        //新加入的因子必须<=上一个因子 并且<=N
        int m = ((top == -1) || (buf[top] > n)) ? n : buf[top];
        for (int i = m; i >= 1; i--) {
            buf[top + 1] = i;
            divide(n - i, buf, top + 1);
        }

    }

    // 测试
    public static void main(String[] args) {

        int n = 10;
        int[] buf = new int[n];

        divide(n, buf, -1);

    }

}