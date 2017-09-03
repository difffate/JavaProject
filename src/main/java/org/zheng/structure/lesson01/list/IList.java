package org.zheng.structure.lesson01.list;

/**
 * 列表模板接口
 * Created by zhengxb on 2017/8/27.
 */
public interface IList<T> {

    boolean pushBack(T newElement);

    boolean popFront();

    Element<T> front();

    int size();
}
