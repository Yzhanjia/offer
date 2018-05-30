package leetcode;
/*
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串
 */
public class Palindrome {
    public static boolean isPalindrome(String s){
        if (s == null){
            return false;
        }
        if (s.equals("")){//空串为真
            return true;
        }

        String s1 = new String();
        s1 = s.toLowerCase();//忽略大小写
        int i = 0;
        int j = s1.length()-1;
        while (i<=j&&i<=s.length()-1&&j>=0){
            while (!((s1.charAt(i)>='0'&&s1.charAt(i)<='9')||
                    (s1.charAt(i)>='a'&&s1.charAt(i)<='z'))){//忽略符号
                i++;
                if (i>=j){//两个指针相遇，已经遍历完字符串，为回文字符串
                    return true;
                }
                if (i>=s.length()){
                    return false;//不存在字母与数字
                }
            }
            while (!((s1.charAt(j)>='0'&&s1.charAt(j)<='9')||
                    (s1.charAt(j)>='a'&&s1.charAt(j)<='z'))){//跳过非字母与数字
                j--;
                if (i>=j){
                    return true;
                }
                if (j<0){//不存在字母与数字
                    return false;
                }
            }
            if (s1.charAt(i) == s1.charAt(j)){//找到一个相同字符，移动头尾指针
                i++;
                j--;
            }else {
                return false;
            }


        }
        return true;

    }
}
