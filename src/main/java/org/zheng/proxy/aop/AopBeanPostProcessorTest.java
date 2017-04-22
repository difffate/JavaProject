package org.zheng.proxy.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zheng.profile.ProfileTest;

/**
 * Create by zxb on 2017/4/22
 */
public class AopBeanPostProcessorTest {
    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        ProfileTest profileTest = (ProfileTest)ac.getBean("profileTest");
        System.out.println("env="+profileTest.getEnvironment());
    }
}
