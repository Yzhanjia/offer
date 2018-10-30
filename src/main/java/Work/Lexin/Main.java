package Work.Lexin;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine();
        String[] strings = tmp.split(" ");
        int n = Integer.valueOf(strings[0]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < strings.length; i++) {
            map.put(Integer.valueOf(strings[i]), null);
//            System.out.println(i);
        }
        if (map.size() == 0) {
            System.out.println("not found");
        }
        printNum(map, n);
    }

    private static void printNum(Map<Integer, Integer> map, int n) {
        Set<Integer> set = new HashSet<>();
        for (Integer i : map.keySet()) {
            if (map.containsKey(n - i) && map.get(i) == null) {
                map.put(i, n - i);
                map.put(n - i, i);
                set.add(i);
                set.add(n - i);
            }
        }
        if (set.size() == 0) {
            System.out.println("not found");
            return;
        }

        Integer[] res = set.toArray(new Integer[set.size()]);
        Arrays.sort(res);
        for (int i = 0; i < res.length; i++) {
            if (set.contains(res[i]) && set.contains(map.get(res[i]))) {
                System.out.println(res[i] + " " + map.get(res[i]));
            }
            set.remove(i);
            set.remove(n - i);
        }
    }
}
