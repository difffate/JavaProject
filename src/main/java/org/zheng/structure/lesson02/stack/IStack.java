package org.zheng.structure.lesson02.stack;

/**
 * Create by zxb on 2017/9/3
 */
public interface IStack<T> {

    void push(T newElement);

    void pop();

    T top();

    int size();
}
