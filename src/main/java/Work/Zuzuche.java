package Work;

public class Zuzuche {
    public static void main(String[] args) {
        for (int i = 3; i <= 1000; i++) {
            int cur = i;
            for (int j = 1; j < i; j++) {
                if (j != 2 && j % 2 == 0) {
                    continue;
                }
                if (i % j == 0 && cur - j >= 0) {
                    cur -= j;
                }
                if (cur == 0) {
                    System.out.println(i);
                    break;
                }

            }
        }
    }
}
