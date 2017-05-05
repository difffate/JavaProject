package org.zheng.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 转账服务
 * Create by zxb on 2017/5/6
 */
@Service
public class BankService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void moneyOut(Long fromUserId, double money) {
    }

    public void moneyIn(Long toUserId, double money) {

    }
}
