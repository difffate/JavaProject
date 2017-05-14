package org.zheng;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zheng.profile.ProfileTest;

/**
 * Create by zxb on 2017/5/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"/beans.xml"})
public class TestDemo {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        ProfileTest profileTest = (ProfileTest) applicationContext.getBean("profileTest");
        System.out.println("Hi, " + profileTest.getEnvironment());
    }
}
