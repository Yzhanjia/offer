package code.ch01._01;

import java.util.Stack;

/**
 * 实现一个栈，在实现基本功能的基础上，再实现返回栈中最小元素的操作
 */
//压入省空间，弹出费时间
public class MyStack1 {
    private Stack<Integer> stackData;//保存当前栈中的元素
    private Stack<Integer> stackMin;//保存每一步的最小值

    public MyStack1() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    //栈为空或者newNum更小，同时压入最小栈
    public void push(int newNum) {
        if (this.stackData.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.getMin()) {
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);//普通的压入操作
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }
        int value = this.stackData.pop();
        if (value == this.getMin()) {//如果栈弹出的值刚好是当前栈的最小值，弹出最小栈中对应值
            this.stackMin.pop();
        }
        return value;
    }

    private int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty!");
        }
        return this.stackMin.peek();//弹出最小栈的栈顶
    }
}
