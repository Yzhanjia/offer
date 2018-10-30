package Work;

import java.util.LinkedList;
import java.util.Scanner;

public class Main0 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(reverse(input));
    }

    private static String reverse(String input) {
        if (input == null || input.equals("") || input.charAt(0) != '/') {
            return null;
        }
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/' && !list.isEmpty() && list.peekLast() == '/') {
                list.removeLast();
            }
            if (i + 2 < input.length() && input.charAt(i) == '/' && input.charAt(i + 1) == '.' && input.charAt(i + 2) == '.') {
                if (!list.isEmpty()) {
//                    System.out.println(list.peekLast());
                    while (!list.isEmpty() && list.getLast() != '/') {
                        list.removeLast();
                        //System.out.println(list.pop());
                    }
                    i = i + 2;
                    continue;
                }
            }
            if (i + 1 < input.length() && input.charAt(i) == '/' && input.charAt(i + 1) == '.') {
                i = i + 1;
                continue;
            }
//            System.out.println(input.charAt(i));
            list.add(input.charAt(i));
        }
        if (list.getLast() == '/') {
            list.removeLast();
        }
        char[] chas = new char[list.size()];
        for (int i = 0; i < chas.length; i++) {
            chas[i] = list.removeFirst();
        }

        //System.out.println(String.valueOf(chas));
        return String.valueOf(chas);
    }

}
