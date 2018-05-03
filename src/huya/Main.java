package huya;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
////        String  s =  scanner.next();
        String s = "虎牙直播中国最大最好的互动直播平台。众多热门高清的游戏直播；上千款游戏，游戏大神齐聚虎牙。星光闪耀，顶尖赛事，综艺娱乐，美女秀场……不一样的精彩直播";
        System.out.println("多个词用半角逗号分开,如:" + getS(s));
    }

    public static String getS(String s){
        List<String> list= new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        list.add("中国");
        list.add("直播");
        list.add("游戏");
        list.add("游戏直播");
        list.add("综艺娱乐");
        list.add("互动直播平台");
        for (int i = 0; i < list.size(); i++) {
            int k = 0;
            for (int j = 0; j < s.length(); j++) {
                if (list.get(i).charAt(k)==s.charAt(j)){
                    k++;
                    j++;
                    if (k==list.get(i).length()){
                        stringBuilder.append(list.get(i)+",");
                        break;
                    }
                    if(list.get(i).charAt(k)!=s.charAt(j)){
                        k = 0;
                        break;
                    }
                }
            }
        }
        if (stringBuilder.capacity()!=0){
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }

        return stringBuilder.toString();

    }
}
