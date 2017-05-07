package org.zheng.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 转账服务
 * Create by zxb on 2017/5/6
 */
@Repository
public class BankRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void moneyOut(Long userId, double money) {
        jdbcTemplate.update("update user_bank_account set balance = balance - ?  where userId=? ", money, userId);
    }

    public void moneyIn(Long userId, double money) {
        jdbcTemplate.update("update user_bank_account set balance = balance + ?  where userId=? ", money, userId);
    }
}
