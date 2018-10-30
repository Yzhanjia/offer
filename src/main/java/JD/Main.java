package JD;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int count = 0;
//        Map<Integer, Integer> aMap = new HashMap<>();
//        Map<Integer, Integer> bMap = new HashMap<>();
//        Map<Integer, Integer> cMap = new HashMap<>();
//        Map<Integer, Boolean> tMap = new HashMap<>();
        long[] aArr = new long[num];
        long[] bArr = new long[num];
        long[] cArr = new long[num];
        boolean[] tArr = new boolean[num];
        for (int i = 0; i < num; i++) {
            long a = scanner.nextLong();
            long b = scanner.nextLong();
            long c = scanner.nextLong();
            aArr[i] = a;
            bArr[i] = b;
            cArr[i] = c;
            tArr[i] = false;
//            aMap.put(i, a);
//            bMap.put(i, b);
//            cMap.put(i, c);
//            tMap.put(i, false);
//            System.out.println("" + a + b + c);
        }
        for (int i = 0; i < num; i++) {
            if (!tArr[i]) {
                for (int j = i + 1; j < num; j++) {
                    if (aArr[j] < aArr[i] && bArr[j] < bArr[i] && cArr[j] < cArr[i]) {
                        count++;
                        tArr[i] = true;
                        continue;
                    }
                    if (aArr[j] > aArr[i] && bArr[j] > bArr[i] && cArr[j] > cArr[i] && !tArr[j]) {
                        count++;
                        tArr[j] = true;
                    }
                }
            }
//            if (!tMap.get(i)) {
//                int a = aMap.get(i);
//                int b = bMap.get(i);
//                int c = cMap.get(i);
//                for (int j = i + 1; j < num; j++) {
//                    if (aMap.get(j) < a && bMap.get(j) < b && cMap.get(j) < c) {
//                        count++;
//                        tMap.put(i, true);
//                        continue;
//                    }
//                    if ((aMap.get(j) > a && bMap.get(j) > b && cMap.get(j) > c && !tMap.get(j)) ){
//                        count++;
//                        tMap.put(j, true);
//                    }
//
//                }
//            }

        }
        System.out.println(count);
    }


}
