package org.zheng.structure.lesson01.list;

/**
 * 链表实现
 * Create by zxb on 2017/8/27
 */
public class ListImpl<T> implements IList<T> {

    private Element<T> first = null;

    private Element<T> last = null;

    private int size = 0;

    @Override
    public boolean pushBack(T newElement) {
        Element<T> element = new Element<>();
        element.setValue(newElement);
        if (size == 0) {
            first = element;
            size++;
            return true;
        }
        if (last == null) {
            last = new Element<>();
            last.setValue(newElement);
            first.setNext(last);
        } else {
            last.setNext(element);
            last = element;
        }
        size++;
        return true;
    }

    @Override
    public boolean popFront() {
        if (size == 0) {
            return false;
        }
        first = first.getNext();
        size--;
        if (size == 0) {
            last = null;
        }
        return true;
    }

    @Override
    public Element<T> front() {
        return first;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        if (first == null) {
            return;
        }
        Element<T> temp = first;
        System.out.print("list={");
        do {
            System.out.print(temp.getValue());
            temp = temp.getNext();
            if (temp != null) {
                System.out.print(", ");
            }
        } while (temp != null);
        System.out.println("}");
    }
}
