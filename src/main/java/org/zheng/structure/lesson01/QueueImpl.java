package org.zheng.structure.lesson01;

import org.zheng.structure.lesson01.list.Element;
import org.zheng.structure.lesson01.list.ListImpl;

/**
 * 队列实现
 * Create by zxb on 2017/8/27
 */
public class QueueImpl<R> implements IQueue<R> {

    private ListImpl<R> list;

    public QueueImpl() {
        this.list = new ListImpl<>();
    }

    @Override
    public boolean push(R newElement) {
        if (list == null) {
            list = new ListImpl<>();
        }
        list.pushBack(newElement);
        return true;
    }

    @Override
    public boolean pop() {
        if (list == null) {
            list = new ListImpl<>();
        }
        list.popFront();
        return true;
    }

    @Override
    public R front() {
        return list.front().getValue();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean display() {
        Element<R> front = list.front();
        System.out.println("列表元素：");
        Element<R> temp = front;
        while (temp != null) {
            System.out.print(temp.getValue());
            temp = temp.getNext();
            System.out.println("\t");
        }
        return true;
    }
}
