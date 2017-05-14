package org.zheng.convert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FormatterTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(context.getBean("team"));
    }
}
//  Team(id=10, names=[a, b, c, d], leader=Member(name=Leon), createDate=2015-01-26,
//  viceLeader=Member(name=Bill), memberCount=10, logo=Champagne, subNames=[11, 22])