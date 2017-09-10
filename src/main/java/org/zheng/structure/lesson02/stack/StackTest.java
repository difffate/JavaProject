package org.zheng.structure.lesson02.stack;

/**
 * Create by zxb on 2017/9/10
 */
public class StackTest {

    public static void main(String[] args) {
        IStack stack = new StackImpl();
        stack.push(4);
        stack.push(3);
        stack.pop();
        stack.pop();
        System.out.println(stack.top());
    }
}
