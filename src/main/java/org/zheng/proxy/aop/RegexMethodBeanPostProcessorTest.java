package org.zheng.proxy.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create by zxb on 2017/4/22
 */
public class RegexMethodBeanPostProcessorTest {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        RegexMethodModel regexMethodModel = (RegexMethodModel) ac.getBean("regexMethodModel");
        regexMethodModel.methodAAA();
        regexMethodModel.methodBBB();
        regexMethodModel.methodTest();
    }
}
