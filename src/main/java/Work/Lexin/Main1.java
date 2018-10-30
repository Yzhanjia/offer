package Work.Lexin;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
//        char i = 97;
//        System.out.println(i);
        Scanner scanner = new Scanner(System.in);
        String old = scanner.nextLine();
        replace(old);
    }

    private static void replace(String old) {
        if (old == null || old.length() == 0) {
            System.out.println(old);
        }
        char[] chars = old.toCharArray();
        int[] count = new int[256];
        int max = 0;
        int index = -1;
        for (int i = 0; i < chars.length; i++) {
            count[chars[i]]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        char[] chars1 = new char[max];

        for (int i = 0; i < max; i++) {
            chars1[i] = (char) index;
        }
        char c = (char) index;
        System.out.println(String.valueOf(chars1) + old.replaceAll(String.valueOf((char) index), ""));
    }
}
