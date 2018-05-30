package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
找出第一个只出现一次的字符
 */
public class Unique {
    public  static int firstUniqChar(String s) {
        if (s.length()==1){
            return 0;
        }
        Set set = new HashSet();
        for (int i = 0; i < s.length()-1 ; i++) {
            while (set.contains(i)) {//若Set中存在当前字符，则当前字符不为所求，寻找下一个
                i++;
            }
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i)==s.charAt(j)){//找到相同字符，将下标存到Set中，下次遇到可直接跳过
                    set.add(i);
                    set.add(j);
                }
            }
            if (!set.contains(i)&&i<s.length()){//若set不存在，证明当前字符为所求，因为上一步亿遍历玩字符串
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("ac&ca"));
    }
}
