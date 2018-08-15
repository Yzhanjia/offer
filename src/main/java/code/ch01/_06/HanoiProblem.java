package code.ch01._06;

import java.util.Stack;

/**
 * 用栈求解汉诺塔问题
 * 不能从最左直接移到最右，也不能将最右栈直接移到最左
 */
public class HanoiProblem {
    public static void main(String[] args) {
        //System.out.println(hanoiProblem1(4, "left", "mid", "right"));
        System.out.println(hanoiProblem2(4, "left", "mid", "right"));
    }

    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    /**
     * 递归完成
     *
     * @param num   当前层数
     * @param left
     * @param mid
     * @param right
     * @param from  出发地
     * @param to    移动的目的地
     * @return
     */
    private static int process(int num, String left, String mid, String right, String from, String to) {
        //只有一层直接从左到右移动
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        if (from.equals(mid) || to.equals(mid)) {//只移动一个位置
            String another = (from.equals(left) || to.equals(left)) ? right : left;//另一极端
            //先将n - 1 层移到另一端
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            //将最下一层移到中间
            System.out.println("Move " + num + " from " + from + " to " + to);
            //再将n - 1 移到中间
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {//移动两个位置
            //将n - 1 层移到另一端
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            //先将最后一层移到中间
            System.out.println("Move " + num + " from " + from + " to " + mid);
            //将n - 1层移回出发地，空出目的地
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            //再将最底层移到另一端
            System.out.println("Move " + num + " from " + mid + " to " + to);
            //递归执行 n - 1层
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    /**
     * 非递归执行
     * 实际动作只有四个 左到中，中到右，右到中，中到左
     */
    public enum Action {
        No, LToM, MToL, MToR, RToM
    }

    /**
     * 小压大，相邻不可逆
     *
     * @param num
     * @param left
     * @param mid
     * @param right
     * @return
     */
    public static int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();
        ls.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);
        for (int i = num; i > 0; i--) {
            ls.push(i);//将全部数据压到最左栈
        }
        Action[] record = {Action.No};
        int step = 0;
        while (rs.size() != num + 1) {//每次只会执行其中一个步骤
            step += fStackToStack(record, Action.MToL, Action.LToM, ls, ms, left, mid);
            step += fStackToStack(record, Action.LToM, Action.LToM, ms, ls, mid, left);
            step += fStackToStack(record, Action.RToM, Action.MToR, ms, rs, mid, right);
            step += fStackToStack(record, Action.MToR, Action.RToM, rs, ms, right, mid);
        }
        return step;
    }

    /**
     *
     * @param record 记录上一次操作
     * @param preNoAct 上一次动作不能为当前操作的逆操作
     * @param nowAct 当前操作
     * @param fStack 原栈
     * @param tStack 目标栈
     * @param from 出发地
     * @param to 目的地
     * @return 返回操作次数 1
     */
    private static int fStackToStack(Action[] record, Action preNoAct, Action nowAct,
                                     Stack<Integer> fStack, Stack<Integer> tStack,
                                     String from, String to) {
        //小压大，相邻不可逆
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;//记录当前操作即下次操作的上一次操作
            return 1;
        }
        return 0;
    }
}
