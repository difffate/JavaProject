package org.zheng.structure.lesson01.list;

/**
 * 链表元素定义
 * Create by zxb on 2017/8/27
 */
public class Element<T> {

    private Element<T> next;

    private T value;

    public Element<T> getNext() {
        return next;
    }

    public void setNext(Element<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
