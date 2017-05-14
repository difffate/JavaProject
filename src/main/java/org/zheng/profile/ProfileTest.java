package org.zheng.profile;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Create by zxb on 2017/4/16
 */
@Data
public class ProfileTest {

    @Value("${environment}")
    private String environment;

    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        ProfileTest profileTest = (ProfileTest) ac.getBean("profileTest");
        System.out.println("env=" + profileTest.getEnvironment());
    }
}
