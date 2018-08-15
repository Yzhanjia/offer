package code.ch01._05;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 * 即用栈将目标栈从小到大排序
 */
public class StackSort {

    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();//弹出一个元素
            while (!help.isEmpty() && help.peek() < cur) {//只有当辅助栈的栈顶大于或等于弹出元素时，才将该元素压入辅助栈
                stack.push(help.pop());//将辅助栈的元素弹出直到栈顶元素大于或等于当前元素，即辅助栈的较小元素只能在较大元素之上
            }
            help.push(cur);
        }
        //重新压回，即逆序，目标栈从大到小排序
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(0);
        stack.push(-1);
        sortStackByStack(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
