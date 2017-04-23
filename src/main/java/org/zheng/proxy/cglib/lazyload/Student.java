package org.zheng.proxy.cglib.lazyload;

import lombok.Data;
import org.springframework.cglib.proxy.Enhancer;

/**
 * Create by zxb on 2017/4/23
 */
@Data
public class Student {

    private int id;

    private String name;

    /**
     * 英语课时间表
     */
    private Schedule EnglishSchedule;

    /**
     * 数学课时间表
     */
    private Schedule MathSchedule;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.EnglishSchedule = createEnglishSchedule();
        this.MathSchedule = createMathSchedule();
    }

    private Schedule createEnglishSchedule() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Schedule.class);
        enhancer.setCallback(new ScheduleLazyLoader());
        return (Schedule) enhancer.create();
    }

    private Schedule createMathSchedule() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Schedule.class);
        enhancer.setCallback(new ScheduleDispatcher());
        return (Schedule) enhancer.create();
    }
}
