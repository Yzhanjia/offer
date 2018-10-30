package Work;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
//        String string = "011000";
        System.out.println(proNum(string));
    }

    private static int proNum(String string) {
        if (string == null || string.length() < 4) {
            return 0;
        }
        if (string.length() == 4) {
            return 1;
        }
        int count = 1;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '0') {
                continue;
            } else if (string.charAt(i) == '1') {
                if (i + 2 < string.length() - 3) {
                    count = count + 2;
                } else if (i + 1 < string.length()) {
                    count++;
                }
            } else if (string.charAt(i) == '2') {
                if (i + 2 < string.length()) {
                    if (string.charAt(i + 1) < '5') {
                        count = count + 2;
                    } else if (string.charAt(i + 1) == '5') {
                        if (string.charAt(i + 2) < '7') {
                            count = count + 2;
                        }
                    }
                } else if (i + 1 < string.length()) {
                    count++;
                }
            } else {
                if (i + 1 < string.length()) {
                    count++;
                }
            }
        }
        return count;
    }

}
