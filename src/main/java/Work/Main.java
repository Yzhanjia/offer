package Work;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println(maxLength("avb"));
    }

    public static int maxLength(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            Set<Character> set = new HashSet<>();
            set.add(string.charAt(i));
            int length = 1;
            for (int j = i + 1; j < string.length(); j++) {
                if (!set.contains(string.charAt(j))) {
                    set.add(string.charAt(j));
                    length++;
                } else {
//                    result = Math.max(result, length);
                    break;
                }
            }
            result = Math.max(result, length);
        }
        return result;
    }
}
