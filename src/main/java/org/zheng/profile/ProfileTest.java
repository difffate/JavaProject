package org.zheng.profile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Create by zxb on 2017/4/16
 */
public class ProfileTest {
    private String environment;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        ProfileTest profileTest = (ProfileTest)ac.getBean("profileTest");
        System.out.println("env="+profileTest.getEnvironment());
    }
}
