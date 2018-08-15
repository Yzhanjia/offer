package code.ch01._02;

import java.util.Stack;

/**
 * 两个栈实现队列
 */

public class TwoStackQueue {
    public Stack<Integer> stackPush;//压入栈
    public Stack<Integer> stackPop;//弹出栈，作为过渡

    public TwoStackQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void add(int pushInt) {
        stackPush.push(pushInt);//直接在压入栈添加
    }

    public int poll() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (stackPop.isEmpty()) {//如果弹出栈不为空，不能压入数据
            while (!stackPush.isEmpty()) {//将压入栈的元素全部压入弹出栈，避免部分没有成功反转
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }
}
