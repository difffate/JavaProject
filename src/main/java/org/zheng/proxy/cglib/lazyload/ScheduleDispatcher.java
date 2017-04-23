package org.zheng.proxy.cglib.lazyload;

import org.springframework.cglib.proxy.Dispatcher;

import java.util.Calendar;

/**
 * Create by zxb on 2017/4/23
 */
public class ScheduleDispatcher implements Dispatcher {

    public Object loadObject() throws Exception {
        System.out.println("before Dispatcher init...you can query from db...");
        Schedule schedule = new Schedule();
        schedule.setCourseName("Math");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,4,1);
        schedule.setCourseTime(calendar.getTime());
        System.out.println("after Dispatcher init...");
        return schedule;
    }
}
