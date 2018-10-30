package code.ch04._14;

/**
 * 跳跃数组
 * arr[i]==k表示可以从i向右跳1~k个距离
 * 返回最少跳几次到最后的位置上
 */
public class Jump {
    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 1, 4};
        System.out.println(jump(arr));
    }

    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int jump = 0;//记录跳跃的步骤
        int cur = 0;//记录如果只能跳jump步，能跳到的位置
        int next = 0;//如果多跳一步能达到的位置
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                jump++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }
}
