package org.zheng.structure.lesson02.stack;

import org.zheng.structure.lesson01.QueueImpl;

/**
 * 用两个队列实现一个栈，要求top()的时间复杂度为O(1)
 * Create by zxb on 2017/9/10
 */
public class StackImpl<T> implements IStack<T> {

    private QueueImpl<T> q1;

    private QueueImpl<T> q2;

    private T topRecord;

    private int size;

    public StackImpl() {
        this.q1 = new QueueImpl<>();
        this.q2 = new QueueImpl<>();
        this.size = 0;
    }

    @Override
    public void push(T newElement) {
        if (q2.size() == 0) {
            q1.push(newElement);
        } else if (q1.size() == 0) {
            q2.push(newElement);
        }
        topRecord = newElement;
        size++;
    }

    @Override
    public void pop() {
        if (size <= 0) {
            return;
        }
        if (q2.size() == 0) {  //从q1导到q2
            if (q1.size() == 1) {  //只有一个元素
                q1.pop();
                topRecord = null;
            }
            while (q1.size() > 1) {
                T front = q1.front();
                //倒2元素为新的栈顶元素
                if (q1.size() == 2) {
                    topRecord = front;
                }
                q2.push(front);
                q1.pop();
            }
            //弹出最后一个元素
            q1.pop();
        } else if (q1.size() == 0) { //从q2导到q1
            if (q2.size() == 1) {  //只有一个元素
                q2.pop();
                topRecord = null;
            }
            while (q2.size() > 1) {
                T front = q2.front();
                //倒2元素为新的栈顶元素
                if (q2.size() == 2) {
                    topRecord = front;
                }
                q1.push(front);
                q2.pop();
            }
            //弹出最后一个元素
            q2.pop();
        }
        size--;
    }

    @Override
    public T top() {
        return topRecord;
    }

    @Override
    public int size() {
        return size;
    }
}
