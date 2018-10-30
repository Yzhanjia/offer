package huya;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        Encypt(s);

    }

    public static int[] parseSampleInput() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] t1 = scanner.nextLine().split(" ");
            int[] t2 = new int[t1.length];
            for (int i = 0; i < t1.length; i++) {
                t2[i] = Integer.valueOf(t1[i]);
            }
            return t2;
        }
        return null;
    }
    public static void Encypt(String s){
        Map<Character, Character> map = new HashMap<Character, Character>();
        StringBuilder stringBuilder = new StringBuilder();
        map.put('1','2');
        map.put('2','4');
        map.put('3','1');
        map.put('4','5');
        map.put('5','3');
        for (int i = 0; i < s.length(); i=i+2) {
            stringBuilder.append(map.get(s.charAt(i)));
            stringBuilder.append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        System.out.println(stringBuilder.toString());
    }
}
