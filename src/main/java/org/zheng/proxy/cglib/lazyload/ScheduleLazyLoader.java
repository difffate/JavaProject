package org.zheng.proxy.cglib.lazyload;

import org.springframework.cglib.proxy.LazyLoader;

import java.util.Calendar;

/**
 * Create by zxb on 2017/4/23
 */
public class ScheduleLazyLoader implements LazyLoader {

    public Object loadObject() throws Exception {
        System.out.println("before LazyLoader init...you can query from db...");
        Schedule schedule = new Schedule();
        schedule.setCourseName("English");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,3,28);
        schedule.setCourseTime(calendar.getTime());
        System.out.println("after LazyLoader init...");
        return schedule;
    }
}
