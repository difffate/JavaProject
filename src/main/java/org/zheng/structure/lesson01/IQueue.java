package org.zheng.structure.lesson01;

/**
 * 队列接口
 * Create by zxb on 2017/8/27
 */
public interface IQueue<R> {

    boolean push(R newElement);

    boolean pop();

    R front();

    int size();

    boolean display();
}
