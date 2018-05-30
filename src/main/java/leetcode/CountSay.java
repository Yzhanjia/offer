package leetcode;
/*
报数序列是指一个整数序列，按照其中的整数的顺序进行报数，
1
11
21
...
1 被读作  "one 1"  ("一个一") , 即 11。
11 被读作 "two 1s" ("两个一"）, 即 21。
21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211
 */
public class CountSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(8));
    }
    public static String countAndSay(int n) {
        if (n==1){
            return "1";
        }
        String result = "1";
        for (int i = 1; i < n; i++) {//找出第n个报数序列
            result = getResult(result);//由第2个开始求到第n个
        }
        return result;

    }

    private static String getResult(String result) {
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
            int i =0;
            while (i<result.length()){
                while (i + 1<result.length()&&result.charAt(i) == result.charAt(i+1)){
                    count++;//记录字符出现次数
                    i++;//指向最后一次出现的重复字符
                }
                stringBuilder.append(count).append(result.charAt(i));//拼接次数与该字符
                i++;//下一字符为新的字符
                count = 1;//将计数器重置
            }


        return stringBuilder.toString();
    }
}
