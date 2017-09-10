package org.zheng.structure.lesson02.combine;

import org.zheng.structure.lesson01.list.Element;
import org.zheng.structure.lesson01.list.IList;
import org.zheng.structure.lesson01.list.ListImpl;

/**
 * 合并两个链表，得到一个新的链表
 * a = 1->2->3->4 和 b = 2->3->5 合并为 c = 1->2->3->4->5
 * 另外只能返回结果c，不能修改a,b两个链表的数据。
 * Create by zxb on 2017/9/10
 */
public class CombineUtil {

    public static <T extends Number> IList<T> combine(IList<T> list1, IList<T> list2) throws Exception {
        if (list1 == null || list1.size() == 0 || list2 == null || list2.size() == 0) {
            throw new Exception("参数错误");
        }
        Element<T> currentPosition1 = list1.front();
        Element<T> currentPosition2 = list2.front();
        //定义最终链表
        IList<T> list = new ListImpl<>();
        //定义最终链表当前末尾元素
        T currentValue = null;
        //定义当前处理的链表序号
        int currentListIndex = currentPosition1.getValue().longValue() <= currentPosition2.getValue().longValue() ? 1 : 2;
        while (currentPosition1 != null || currentPosition2 != null) {
            if (currentPosition1 == null) {
                currentListIndex = 2;
            }
            if (currentPosition2 == null) {
                currentListIndex = 1;
            }
            if (currentListIndex == 1) {
                if (currentPosition2 == null || currentPosition1.getValue().longValue() <= currentPosition2.getValue().longValue()) {
                    //跳过相同元素
                    if (currentValue == null || (currentValue != null && currentValue.longValue() != currentPosition1.getValue().longValue())) {
                        currentValue = currentPosition1.getValue();
                        list.pushBack(currentValue);
                    }
                    currentPosition1 = currentPosition1.getNext();
                    //如果下一个元素相同，则另一个链表的当前指针后移
                    if (currentPosition1 != null && currentPosition2 != null && currentPosition1.getValue().longValue() == currentPosition2.getValue().longValue()) {
                        currentPosition2 = currentPosition2.getNext();
                    }
                } else {
                    currentListIndex = 2;
                }
            } else {
                if (currentPosition1 == null || currentPosition2.getValue().longValue() <= currentPosition1.getValue().longValue()) {
                    //跳过相同元素
                    if (currentValue == null || (currentValue != null && currentValue.longValue() != currentPosition2.getValue().longValue())) {
                        currentValue = currentPosition2.getValue();
                        list.pushBack(currentValue);
                    }
                    currentPosition2 = currentPosition2.getNext();
                    //如果下一个元素相同，则另一个链表的当前指针后移
                    if (currentPosition1 != null && currentPosition2 != null && currentPosition1.getValue().longValue() == currentPosition2.getValue().longValue()) {
                        currentPosition1 = currentPosition1.getNext();
                    }
                } else {
                    currentListIndex = 1;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        IList<Integer> list1 = new ListImpl<>();
        list1.pushBack(1);
        list1.pushBack(2);
        list1.pushBack(3);
        list1.pushBack(4);
        IList<Integer> list2 = new ListImpl<>();
        list2.pushBack(2);
        list2.pushBack(3);
        list2.pushBack(5);
        IList<Integer> list3 = CombineUtil.combine(list1, list2);
        list3.display();
    }
}
