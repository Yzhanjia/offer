package leetcode;
/*
反转数字
 */
public class ReverseInt {
    public int reverse(int x) {
        boolean flag = false;
        int reverseX = 0;
        if (x<0){
            flag = true;
            x = -x;
        }
        while (x!=0){
            int tmp = reverseX * 10 + x % 10;
            if (tmp / 10 != reverseX){//溢出
                return 0;
            }
            reverseX = tmp;
            x /=10;
        }
        if (flag){
            reverseX = -reverseX;
        }
       return reverseX;
    }
}
