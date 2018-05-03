package leetcode;
/*
给定一个 haystack 字符串和一个 needle 字符串，
在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
如果不存在，则返回  -1

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0
 */
public class StrStr {
    public static void main(String[] args) {
        System.out.println(strStr("mississippi"
                ,"issip"));
    }
    public static int strStr(String haystack, String needle) {
        if (haystack==null||(haystack.length()==0&&needle.length()!=0)
                ||needle==null||haystack.length()<needle.length()){
            return -1;//其中之一为null或needle长度大于haystack，或者haystack为空串，needle不为空串，均为空串则为0
        }
        if (needle.length()==0){//若needle为空串
            return 0;
        }
        int i = 0,j =0,count = 0;
        while (i<haystack.length()){
            if (haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
                count++;//找到相同字符，移动两个指针，并记录匹配个数
            }
            if (j==needle.length()){
                return i -needle.length();//完全匹配，返回haystack对应下标
            }
            if (i>=haystack.length()){
                return -1;//没有匹配，且haystack已遍历完
            }
            if (haystack.charAt(i)!=needle.charAt(j)){
                if (count!=0){
                    i = i - count;//回溯，从上次匹配的第一个字符的下一字符开始
                    j = 0;
                    count = 0;//将匹配个数重置为0
                }
                i++;
            }
        }
        return -1;


    }
}
