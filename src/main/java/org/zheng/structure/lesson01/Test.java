package org.zheng.structure.lesson01;

/**
 * Create by zxb on 2017/8/27
 */
public class Test {

    public static void main(String[] args) {
        IQueue<String> queue = new QueueImpl<>();
        queue.push("one");
        queue.push("two");
        queue.push("three");
        queue.pop();
        System.out.println("size="+queue.size());
        System.out.println("front="+queue.front());
        queue.display();
    }
}
