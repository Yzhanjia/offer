package leetcode;
/*
反转字符串
 */
public class ReverseString {
    public String reverseString(String s) {
        if (s==null||s.length()<0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i >=0; i--) {//从字符串的最后一字符开始
            sb.append(s.charAt(i));
        }
        return sb.toString();

    }
}
