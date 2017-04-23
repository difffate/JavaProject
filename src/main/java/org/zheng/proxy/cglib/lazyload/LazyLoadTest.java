package org.zheng.proxy.cglib.lazyload;

/**
 * 延迟加载属性
 * Create by zxb on 2017/4/23
 */
public class LazyLoadTest {

    public static void main(String[] args) {
        Student student = new Student(666, "XiaoMing");
        System.out.println("id=" + student.getId());
        System.out.println("name=" + student.getName());
        // LazyLoader 只有第一次，Dispatcher是每次都会进loadObject的方法
        System.out.println("========First Get  EnglishSchedule ========");
        System.out.println(student.getEnglishSchedule());
        System.out.println();
        System.out.println("========First Get  MathSchedule ========");
        System.out.println(student.getMathSchedule());
        System.out.println();
        System.out.println("========Second Get  EnglishSchedule ========");
        System.out.println(student.getEnglishSchedule());
        System.out.println();
        System.out.println("========Second Get  MathSchedule ========");
        System.out.println(student.getMathSchedule());
    }
}
