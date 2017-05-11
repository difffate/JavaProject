package org.zheng.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create by zxb on 2017/5/6
 */
public class BankTransferController {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        BankService bankService = applicationContext.getBean(BankService.class);
        bankService.transfer(111, 222, 200);
    }
}
