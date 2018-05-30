package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    
    public static boolean isAnagram(String s, String t) {
        if (s.equals(t)){
            return true;
        }
        if (s.length()!=t.length()){
            return false;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),1);
            }else {
               map.put(s.charAt(i),map.get(s.charAt(i))+1)  ;
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))){
                return false;
            }else if (map.get(t.charAt(i))==0){
                return false;
            }else {
                map.put(t.charAt(i),map.get(t.charAt(i))-1);
            }
        }
        return true;

    }
}
